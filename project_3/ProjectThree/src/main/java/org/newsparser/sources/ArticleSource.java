package org.newsparser.sources;

import java.io.IOException;

/**
 * The {@code ArticleSource} interface represents a source of articles in JSON format.
 * <p>
 * Implementations of this interface are expected to provide the method to retrieve
 * article data as a JSON-formatted string.
 */
public interface ArticleSource {

    /**
     * Retrieves a JSON string containing article data.
     * <p>
     * This method is expected to be implemented by classes that represent different
     * sources of articles (a news API, a file, etc.)
     *
     * @return a JSON string containing article data
     * @throws IOException if an I/O error occurs during the retrieval of the JSON data
     */
    String getJsonString() throws IOException;
}
