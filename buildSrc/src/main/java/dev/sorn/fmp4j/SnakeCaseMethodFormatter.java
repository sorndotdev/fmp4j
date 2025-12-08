package dev.sorn.fmp4j;

import com.diffplug.spotless.FormatterStep;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.expr.AnnotationExpr;
import com.github.javaparser.ParserConfiguration.LanguageLevel;
import java.io.File;
import java.io.Serial;
import java.util.List;
import java.util.regex.Pattern;


public class SnakeCaseMethodFormatter implements FormatterStep {
    @Serial
    private static final long serialVersionUID = 1L;

    // Set of annotations that identify a method as a unit test
    private static final List<String> TEST_ANNOTATIONS = List.of(
            "Test",              // JUnit 4 & 5
            "ParameterizedTest", // JUnit 5
            "RepeatedTest",      // JUnit 5
            "TestFactory"        // JUnit 5
    );

    private static final Pattern SNAKE_CASE_PATTERN = Pattern.compile("^[a-z][a-z0-9_]*$");

    private static final ParserConfiguration parserConfig = new ParserConfiguration()
            .setLanguageLevel(LanguageLevel.JAVA_17);;

    @Override
    public String getName() {
        return "Unit test Naming Enforcement (snake_case)";
    }

    @Override
    public String format(String rawUnix, File file) throws Exception {
        // 1. Parse the source code into an AST
        StaticJavaParser.setConfiguration(parserConfig);
        CompilationUnit cu = StaticJavaParser.parse(rawUnix);

        // 2. Walk through all method declarations in the file
        boolean changed = false;
        for (MethodDeclaration method : cu.findAll(MethodDeclaration.class)) {
            if (isUnitTest(method)) {
                String oldName = method.getNameAsString();

                // 3. Check if the name complies with snake_case
                if (!SNAKE_CASE_PATTERN.matcher(oldName).matches()) {
                    String newName = toSnakeCase(oldName);
                    method.setName(newName);
                    changed = true;
                }
            }
        }

        // 4. Return the modified source only if changes were made, else return original
        // (Parsing/printing can sometimes shift whitespace, so we prefer returning original if untouched)
        return changed ? cu.toString() : rawUnix;
    }

    /**
     * Checks if the method is annotated with one of the known test annotations.
     */
    private boolean isUnitTest(MethodDeclaration method) {
        for (AnnotationExpr annotation : method.getAnnotations()) {
            if (TEST_ANNOTATIONS.contains(annotation.getNameAsString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a camelCase string to snake_case.
     * Example: "testUserProfile" -> "test_user_profile"
     */
    private String toSnakeCase(String input) {
        // Regex to look for instances of LowerUpper (e.g. "tU") and insert underscore
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";

        // Apply replacement and convert to lowercase
        return input.replaceAll(regex, replacement).toLowerCase();
    }

    @Override
    public void close() throws Exception {

    }
}

