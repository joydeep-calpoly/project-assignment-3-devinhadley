package org.newsparser.databind;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for {@link SimpleArticle}. This class verifies the validation logic for
 * {@link SimpleArticle} instances, ensuring that articles are only considered valid
 * if required fields are properly populated.
 */
class SimpleArticleTest {

    /**
     * Tests that a {@link SimpleArticle} with all valid fields is considered valid.
     * Verifies that the {@link SimpleArticle#isValid()} method returns true when all fields are set.
     */
    @Test
    void testIsValidWithAllValidFields() {
        SimpleArticle article = new SimpleArticle(
                "Exploring the Cosmos",
                "An article on new astronomical discoveries.",
                "2023-09-20 12:30:45.123456",
                "https://www.space.com/exploring-the-cosmos"
        );

        assertTrue(article.isValid(), "Expected the article to be valid when all fields are populated.");
    }

    /**
     * Tests that a {@link SimpleArticle} with a null title is considered invalid.
     * Verifies that the {@link SimpleArticle#isValid()} method returns false when the title is null.
     */
    @Test
    void testIsValidWithNullTitle() {
        SimpleArticle article = new SimpleArticle(
                null,
                "A discussion on the latest in quantum physics.",
                "2023-09-20 12:30:45.123456",
                "https://www.sciencenews.org/quantum-physics"
        );

        assertFalse(article.isValid(), "Expected the article to be invalid due to null title.");
    }

    /**
     * Tests that a {@link SimpleArticle} with an empty title is considered invalid.
     * Verifies that the {@link SimpleArticle#isValid()} method returns false when the title is an empty string.
     */
    @Test
    void testIsValidWithEmptyTitle() {
        SimpleArticle article = new SimpleArticle(
                "",
                "A brief overview of the Mars rover mission.",
                "2023-09-20 12:30:45.123456",
                "https://www.nasa.gov/mars-rover-mission"
        );

        assertFalse(article.isValid(), "Expected the article to be invalid due to empty title.");
    }

    /**
     * Tests that a {@link SimpleArticle} with a null description is considered invalid.
     * Verifies that the {@link SimpleArticle#isValid()} method returns false when the description is null.
     */
    @Test
    void testIsValidWithNullDescription() {
        SimpleArticle article = new SimpleArticle(
                "Mars Rover Mission",
                null,
                "2023-09-20 12:30:45.123456",
                "https://www.nasa.gov/mars-rover-mission"
        );

        assertFalse(article.isValid(), "Expected the article to be invalid due to null description.");
    }

    /**
     * Tests that a {@link SimpleArticle} with an empty description is considered invalid.
     * Verifies that the {@link SimpleArticle#isValid()} method returns false when the description is an empty string.
     */
    @Test
    void testIsValidWithEmptyDescription() {
        SimpleArticle article = new SimpleArticle(
                "Mars Rover Mission",
                "",
                "2023-09-20 12:30:45.123456",
                "https://www.nasa.gov/mars-rover-mission"
        );

        assertFalse(article.isValid(), "Expected the article to be invalid due to empty description.");
    }

    /**
     * Tests that a {@link SimpleArticle} with a null published date is considered invalid.
     * Verifies that the {@link SimpleArticle#isValid()} method returns false when publishedAt is null.
     */
    @Test
    void testIsValidWithNullPublishedAt() {
        SimpleArticle article = new SimpleArticle(
                "The Future of Space Travel",
                "An article discussing advancements in space technology.",
                null,
                "https://www.spacex.com/future-space-travel"
        );

        assertFalse(article.isValid(), "Expected the article to be invalid due to null publishedAt.");
    }

    /**
     * Tests that a {@link SimpleArticle} with a null URL is considered invalid.
     * Verifies that the {@link SimpleArticle#isValid()} method returns false when the URL is null.
     */
    @Test
    void testIsValidWithNullUrl() {
        SimpleArticle article = new SimpleArticle(
                "Black Hole Mysteries",
                "Exploring the mysteries of black holes.",
                "2023-09-20 12:30:45.123456",
                null
        );

        assertFalse(article.isValid(), "Expected the article to be invalid due to null URL.");
    }

    /**
     * Tests that a {@link SimpleArticle} with an empty URL is considered invalid.
     * Verifies that the {@link SimpleArticle#isValid()} method returns false when the URL is an empty string.
     */
    @Test
    void testIsValidWithEmptyUrl() {
        SimpleArticle article = new SimpleArticle(
                "Black Hole Mysteries",
                "Exploring the mysteries of black holes.",
                "2023-09-20 12:30:45.123456",
                ""
        );

        assertFalse(article.isValid(), "Expected the article to be invalid due to empty URL.");
    }
}