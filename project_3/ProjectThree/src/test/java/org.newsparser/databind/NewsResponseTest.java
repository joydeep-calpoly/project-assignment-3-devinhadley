package org.newsparser.databind;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link NewsResponse}. This class verifies the validity checks for
 * {@link NewsResponse} instances, ensuring that required fields (status and articles) are present.
 */
class NewsResponseTest {

    /**
     * Tests that a {@link NewsResponse} with a valid status and a non-empty list of articles is valid.
     * Verifies that the {@link NewsResponse#isValid()} method returns true.
     */
    @Test
    void testIsValidWithValidResponse() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(null, "Author", "Title", "Description", "https://example.com", "https://example.com/image.jpg", new Date(), "Content"));

        NewsResponse newsResponse = new NewsResponse("ok", 1, articles);

        assertTrue(newsResponse.isValid(), "Expected NewsResponse to be valid when status and articles are correctly set.");
    }

    /**
     * Tests that a {@link NewsResponse} with a null status is invalid.
     * Verifies that the {@link NewsResponse#isValid()} method returns false when the status is null.
     */
    @Test
    void testIsValidWithNullStatus() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(null, "Author", "Title", "Description", "https://example.com", "https://example.com/image.jpg", new Date(), "Content"));

        NewsResponse newsResponse = new NewsResponse(null, 1, articles);

        assertFalse(newsResponse.isValid(), "Expected NewsResponse to be invalid when status is null.");
    }

    /**
     * Tests that a {@link NewsResponse} with an empty status is invalid.
     * Verifies that the {@link NewsResponse#isValid()} method returns false when the status is an empty string.
     */
    @Test
    void testIsValidWithEmptyStatus() {
        List<Article> articles = new ArrayList<>();
        articles.add(new Article(null, "Author", "Title", "Description", "https://example.com", "https://example.com/image.jpg", new Date(), "Content"));

        NewsResponse newsResponse = new NewsResponse("", 1, articles);

        assertFalse(newsResponse.isValid(), "Expected NewsResponse to be invalid when status is empty.");
    }

    /**
     * Tests that a {@link NewsResponse} with a null articles list is invalid.
     * Verifies that the {@link NewsResponse#isValid()} method returns false when the articles list is null.
     */
    @Test
    void testIsValidWithNullArticlesList() {
        NewsResponse newsResponse = new NewsResponse("ok", 1, null);

        assertFalse(newsResponse.isValid(), "Expected NewsResponse to be invalid when articles list is null.");
    }

    /**
     * Tests that a {@link NewsResponse} with an empty articles list is invalid.
     * Verifies that the {@link NewsResponse#isValid()} method returns false when the articles list is empty.
     */
    @Test
    void testIsValidWithEmptyArticlesList() {
        List<Article> articles = new ArrayList<>();
        NewsResponse newsResponse = new NewsResponse("ok", 1, articles);

        assertFalse(newsResponse.isValid(), "Expected NewsResponse to be invalid when articles list is empty.");
    }

}
