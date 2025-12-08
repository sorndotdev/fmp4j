package dev.sorn.fmp4j

import org.gradle.api.Plugin
import org.gradle.api.Project

class CoveragePlugin implements Plugin<Project> {
    void apply(Project project) {
        project.tasks.register('printCoverageFromCsv') {
            dependsOn project.tasks.named('jacocoTestReport')
            doLast {
                def csvFile = project.file("${project.buildDir}/reports/jacoco/test/jacocoTestReport.csv")
                if (!csvFile.exists()) {
                    println "Jacoco CSV report not found!"
                    return
                }

                int totalCovered = 0
                int totalMissed = 0

                csvFile.withReader { reader ->
                    def lines = reader.readLines()
                    // Skip header line (index 0)
                    lines.drop(1).each { line ->
                        def columns = line.split(',')
                        if (columns.size() >= 5) {  // Check columns exist
                            int missed = columns[3].toInteger()
                            int covered = columns[4].toInteger()
                            totalMissed += missed
                            totalCovered += covered
                        }
                    }
                }

                int total = totalCovered + totalMissed
                def coveragePercent = total > 0 ? (totalCovered * 100.0 / total) : 0

                println "\u001B[32mCode coverage: ${String.format('%.2f', coveragePercent)}%\u001B[0m"
            }
        }

        project.tasks.named('build').configure {
            finalizedBy 'printCoverageFromCsv'
        }
    }
}