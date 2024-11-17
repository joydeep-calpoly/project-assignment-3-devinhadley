package org.newsparser.databind;

import org.newsparser.utils.Validatable;

import java.util.List;

/**
 * Represents a response containing news articles, typically retrieved from a news API.
 * This class contains information on the status of the response, the total number of results,
 * and a list of articles.
 * Implements {@link Validatable} to ensure validity of response fields.
 */
public class NewsResponse implements Validatable {

    private String status;
    private int totalResults;
    private List<Article> articles;

    /**
     * Default constructor for NewsResponse.
     */
    public NewsResponse() {}

    /**
     * Constructs a NewsResponse instance with the specified status, total results, and list of articles.
     *
     * @param status       the status of the news response (e.g., "ok" or "error")
     * @param totalResults the total number of results available in the response
     * @param articles     the list of articles contained in the response
     */
    public NewsResponse(String status, int totalResults, List<Article> articles) {
        this.status = status;
        this.totalResults = totalResults;
        this.articles = articles;
    }

    /**
     * Checks if the NewsResponse object is valid.
     * A valid NewsResponse should have a non-null, non-empty status and a non-empty list of articles.
     *
     * @return true if the NewsResponse is valid, otherwise false
     */
    @Override
    public boolean isValid() {
        return status != null && !status.isEmpty() && articles != null && !articles.isEmpty();
    }

    /**
     * Gets the status of the news response.
     *
     * @return the status as a String
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the news response.
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the total number of results in the news response.
     *
     * @return the total number of results
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * Sets the total number of results in the news response.
     *
     * @param totalResults the total number of results to set
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Gets the list of articles in the news response.
     *
     * @return the list of articles
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * Sets the list of articles in the news response.
     *
     * @param articles the list of articles to set
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * Returns a string representation of the NewsResponse object.
     *
     * @return a string representation of the NewsResponse, including status, total results, and articles
     */
    @Override
    public String toString() {

        return "NewsResponse [status=" + status + ", totalResults=" + totalResults + ", articles=" + articles + "]";

    }

    public void displayArticles() {
        for (Article article : this.getArticles()) {
            System.out.println("\n--- Article ---");
            System.out.println("Title: " + article.getTitle());
            System.out.println("Description: " + article.getDescription());
            System.out.println("Published Date: " + article.getPublishedDateTime());
            System.out.println("URL: " + article.getUrl());
        }
    }
}
