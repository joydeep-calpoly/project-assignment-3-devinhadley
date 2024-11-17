package org.newsparser.sources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * The {@code UrlArticleSource} class implements the {@link ArticleSource} interface
 * and provides the functionality to retrieve article data from a specified URL.
 */
public class URLJsonSource implements ArticleSource {
    private static final String DEFAULT_API_URL = "http://newsapi.org/v2/top-headlines?country=us&apiKey=cff43eceb1d44ccf957632ba39fbe4d5";
    private final String url;

    public URLJsonSource() {
        this.url = DEFAULT_API_URL;
    }

    /**
     * Constructs a {@code UrlArticleSource} with the specified URL.
     *
     * @param url the URL from which to retrieve the article data
     */
    public URLJsonSource(String url) {
        this.url = url;
    }


    /**
     * Retrieves a JSON string by making an HTTP GET request to the specified URL.
     * <p>
     * This method opens an HTTP connection to the given URL, reads the response
     * data, and returns it as a JSON-formatted string.
     *
     * @return a JSON string containing article data
     * @throws IOException if an I/O error occurs during the connection or data retrieval
     */
    @Override
    public String getJsonString() throws IOException {
        StringBuilder jsonResponse = new StringBuilder();
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        Scanner scanner = new Scanner(connection.getInputStream());
        while (scanner.hasNext()) {
            jsonResponse.append(scanner.nextLine());
        }
        scanner.close();
        return jsonResponse.toString();
    }
}