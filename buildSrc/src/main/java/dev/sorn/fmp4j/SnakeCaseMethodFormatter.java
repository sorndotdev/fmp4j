package dev.sorn.fmp4j;

import com.diffplug.spotless.FormatterStep;
import java.io.File;
import java.io.Serial;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SnakeCaseMethodFormatter implements FormatterStep {
    @Serial
    private static final long serialVersionUID = 1L;

    // Set of annotations that identify a method as a unit test
    private static final Pattern TEST_METHOD_PATTERN = Pattern.compile(
            "(@(?:Test|ParameterizedTest|RepeatedTest|TestFactory)(?:[\\s\\S](?!;|\\{))*?\\s+)([a-zA-Z0-9_]+)(\\s*\\()"
    );

    private static final Pattern SNAKE_CASE_PATTERN = Pattern.compile("^[a-z][a-z0-9_]*$");

    @Override
    public String getName() {
        return "Unit test Naming Enforcement (snake_case)";
    }

    @Override
    public String format(String rawUnix, File file) throws Exception {
        Matcher matcher = TEST_METHOD_PATTERN.matcher(rawUnix);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String context = matcher.group(1); // Annotation + modifiers + return type
            String methodName = matcher.group(2); // Current method name
            String paramsStart = matcher.group(3); // "("

            // Only replace if it fails snake_case check
            if (!SNAKE_CASE_PATTERN.matcher(methodName).matches()) {
                String newName = toSnakeCase(methodName);
                // Replace the name group, preserving context and params
                matcher.appendReplacement(sb, Matcher.quoteReplacement(context + newName + paramsStart));
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
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
        return input.replaceAll(regex, replacement).toLowerCase(Locale.ROOT);
    }

    @Override
    public void close() throws Exception {
        // No resources to close
    }
}

