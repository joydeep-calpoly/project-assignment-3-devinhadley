package org.newsparser.databind;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link Article}. This class verifies the validation of articles.
 */
class ArticleTest {

    private final Source source = new Source("source-id", "source-name");

    /**
     * Tests creation of an {@link Article} instance with valid values for all fields.
     * Verifies that all attributes are correctly set.
     */
    @Test
    void testValidArticleCreation() {
        String author = "Author Name";
        String title = "Test Title";
        String description = "Test Description";
        String url = "https://example.com";
        String urlToImage = "https://example.com/image.jpg";
        Date publishedAt = new Date();
        String content = "Test content.";

        Article article = new Article(source, author, title, description, url, urlToImage, publishedAt, content);

        assertEquals(title, article.getTitle());
        assertEquals(description, article.getDescription());
        assertEquals(url, article.getUrl());
        assertEquals(publishedAt, article.getPublishedDateTime());
    }

    /**
     * Tests the {@link Article#isValid()} method with all required fields populated.
     * Ensures that the article is valid when all fields are provided.
     */
    @Test
    void testIsValidWithAllRequiredFields() {
        String author = "Author Name";
        String title = "Test Title";
        String description = "Test Description";
        String url = "https://example.com";
        String urlToImage = "https://example.com/image.jpg";
        Date publishedAt = new Date();
        String content = "Test content.";

        Article article = new Article(source, author, title, description, url, urlToImage, publishedAt, content);

        assertTrue(article.isValid(), "Article with all required fields should be valid.");
    }

    /**
     * Tests that {@link Article#isValid()} returns false when the title is missing.
     * Verifies that the article is considered invalid without a title.
     */
    @Test
    void testIsValidReturnsFalseForMissingTitle() {
        String author = "Author Name";
        String description = "Test Description";
        String url = "https://example.com";
        Date publishedAt = new Date();
        String content = "Test content.";

        Article article = new Article(source, author, null, description, url, null, publishedAt, content);

        assertFalse(article.isValid(), "Article with missing title should be invalid.");
    }

    /**
     * Tests that {@link Article#isValid()} returns false when the description is missing.
     * Verifies that the article is considered invalid without a description.
     */
    @Test
    void testIsValidReturnsFalseForMissingDescription() {
        String author = "Author Name";
        String title = "Test Title";
        String url = "https://example.com";
        Date publishedAt = new Date();
        String content = "Test content.";

        Article article = new Article(source, author, title, null, url, null, publishedAt, content);

        assertFalse(article.isValid(), "Article with missing description should be invalid.");
    }

    /**
     * Tests that {@link Article#isValid()} returns false when the URL is missing.
     * Verifies that the article is considered invalid without a URL.
     */
    @Test
    void testIsValidReturnsFalseForMissingUrl() {
        String author = "Author Name";
        String title = "Test Title";
        String description = "Test Description";
        Date publishedAt = new Date();
        String content = "Test content.";

        Article article = new Article(source, author, title, description, null, null, publishedAt, content);

        assertFalse(article.isValid(), "Article with missing URL should be invalid.");
    }

    /**
     * Tests that {@link Article#isValid()} returns false when the published date is missing.
     * Verifies that the article is considered invalid without a published date.
     */
    @Test
    void testIsValidReturnsFalseForMissingPublishedDate() {
        String author = "Author Name";
        String title = "Test Title";
        String description = "Test Description";
        String url = "https://example.com";
        String urlToImage = "https://example.com/image.jpg";
        String content = "Test content.";

        Article article = new Article(source, author, title, description, url, urlToImage, null, content);

        assertFalse(article.isValid(), "Article with missing published date should be invalid.");
    }

}