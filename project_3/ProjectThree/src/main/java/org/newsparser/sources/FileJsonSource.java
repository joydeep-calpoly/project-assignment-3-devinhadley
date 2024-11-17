package org.newsparser.sources;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * The {@code FileArticleSource} class implements the {@link ArticleSource} interface
 * and provides the functionality to retrieve article data from a file located in
 * the resources folder.
 */
public class FileJsonSource implements ArticleSource {
    private final String fileName;

    /**
     * Constructs a {@code FileArticleSource} with the specified file name.
     *
     * @param fileName the name of the file from which to retrieve the article data
     */
    public FileJsonSource(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Retrieves a JSON string from the specified file in the resources folder.
     * <p>
     * This method attempts to open the file as an {@link InputStream}, read its
     * contents, and return the data as a JSON-formatted string.
     *
     * @return a JSON string containing article data
     * @throws IOException if the file is not found or an I/O error occurs during reading
     */
    @Override
    public String getJsonString() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new IOException("File not found in resources folder: " + fileName);
        }

        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
