package dev.sorn.fmp4j;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;


class SnakeCaseMethodFormatterTest {

    private SnakeCaseMethodFormatter formatter;

    @BeforeEach
    void setUp() {
        formatter = new SnakeCaseMethodFormatter();
    }

    @Test
    void should_rename_camel_case_unit_test_to_snake_case() throws Exception {
        // Given: A class with a camelCase @Test method
        String source = """
                public class MyTest {
                    @Test
                    public void testLoginFeature() {
                        System.out.println("Testing...");
                    }
                }
                """;

        // When
        String result = formatter.format(source, new File("dummy.java"));

        // Then
        assertTrue(result.contains("void test_login_feature()"),
                "The method name should be converted to snake_case");
        assertFalse(result.contains("testLoginFeature"),
                "The old camelCase name should no longer exist");
    }

    @Test
    void should_not_rename_non_test_methods() throws Exception {
        // Given: A class with a helper method (no @Test annotation)
        String source = """
                public class MyTest {
                    public void helperMethod() {
                        // do something
                    }
                }
                """;

        // When
        String result = formatter.format(source, null);

        // Then
        assertTrue(result.contains("void helperMethod()"),
                "Non-test methods should retain their original name");
    }

    @Test
    void should_ignore_already_snake_case_tests() throws Exception {
        // Given: A class with an existing snake_case test
        String source = """
                public class MyTest {
                    @Test
                    public void test_existing_snake_case() {
                    }
                }
                """;

        // When
        String result = formatter.format(source, null);

        // Then: The method returns the original string if no changes were made
        assertEquals(source, result,
                "The source code should remain unchanged if the name is already correct");
    }

    @Test
    void should_handle_mixed_methods_correctly() throws Exception {
        // Given: One test to rename, one helper to ignore, one test already correct
        String source = """
                public class MixedTest {
                    @Test
                    public void shouldCalculateSum() {}
                    
                    public void setupDatabase() {}
                    
                    @Test
                    public void test_already_good() {}
                }
                """;

        // When
        String result = formatter.format(source, null);

        // Then
        assertAll("Verify all method states",
                () -> assertTrue(result.contains("void should_calculate_sum()"), "CamelCase test should be renamed"),
                () -> assertTrue(result.contains("void setupDatabase()"), "Helper method should not change"),
                () -> assertTrue(result.contains("void test_already_good()"), "Snake case test should stay snake case")
        );
    }

    @Test
    void should_handle_complex_test_annotations() throws Exception {
        // Given: One test to rename, one helper to ignore, one test already correct
        String source = """
                public class MixedTest {
                    @ParameterizedTest
                    @MethodSource("someFactoryMethod")
                    void validValue() {
                        //do something
                    }
                }
                """;

        // When
        String result = formatter.format(source, null);

        // Then
        assertAll("Verify all method states",
                () -> assertTrue(result.contains("void valid_value()"), "CamelCase test should be renamed"),
                () -> assertTrue(result.contains("@ParameterizedTest"), "Annotation should not be moved"),
                () -> assertTrue(result.contains("@MethodSource(\"someFactoryMethod\")"), "Annotation should not be moved")
        );
    }

    @Test
    void should_handle_inline_comment() throws Exception {
        // Given: One test to rename, one helper to ignore, one test already correct
        String source = """
                class InlineCommentTest {
                     @Test
                     void doSomethingHere() {
                         Stream.of(
                                 "" // empty string
                                 );
                     }
                }
                """;

        // When
        String result = formatter.format(source, null);

        // Then
        assertAll("Verify all method states",
                () -> assertTrue(result.contains("do_something_here"), "CamelCase test should be renamed"),
                () -> assertTrue(result.contains("\"\" // empty string"), "Inline comment should remain in place")
                );
    }
}

