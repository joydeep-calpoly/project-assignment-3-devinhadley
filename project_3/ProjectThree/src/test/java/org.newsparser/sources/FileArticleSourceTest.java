package org.newsparser.sources;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link FileJsonSource}. This class verifies functionality for reading
 * JSON data from files, including handling cases where the file does not exist.
 */
public class FileArticleSourceTest {

    /** The name of the test JSON file used in testing. */
    static final String TEST_FILE_NAME = "test/test.json";

    /**
     * Tests reading JSON content from a file using {@link FileJsonSource}.
     * Verifies that the content read from the file matches the expected JSON string.
     */
    @Test
    public void testCanGetJsonFromFile() {
        FileJsonSource source = new FileJsonSource(TEST_FILE_NAME);
        String expected = "{\"test\": \"passed\"}";
        try {
            assertEquals(expected, source.getJsonString());
        } catch (IOException e) {
            System.out.println(TEST_FILE_NAME + " not found in resources folder.");
        }
    }

    /**
     * Tests handling of an attempt to read from a non-existent file.
     * Verifies that an {@link IOException} is thrown with the appropriate error message
     * when the file is not found.
     */
    @Test
    public void testGetJsonFromFileThatDoesntExist() {
        final String fileThatDoesntExistName = "aFileThatDoesntExist.json";
        Exception exception = assertThrows(IOException.class, () -> {
            String jsonString = new FileJsonSource(fileThatDoesntExistName).getJsonString();
        });
        assertEquals(exception.getMessage(), "File not found in resources folder: " + fileThatDoesntExistName);
    }
}