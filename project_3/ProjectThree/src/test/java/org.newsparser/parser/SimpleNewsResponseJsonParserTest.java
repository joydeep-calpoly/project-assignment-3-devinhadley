package org.newsparser.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.newsparser.TestHandler;
import org.newsparser.databind.SimpleArticle;
import org.newsparser.parsers.SimpleNewsResponseJsonParser;
import org.newsparser.sources.ArticleSource;
import org.newsparser.sources.FileJsonSource;
import org.newsparser.sources.URLJsonSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

class SimpleNewsResponseJsonParserTest {

    private Logger logger;
    private TestHandler testHandler;

    @BeforeEach
    public void setUp() {
        logger = Logger.getLogger(SimpleNewsResponseJsonParserTest.class.getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

        testHandler = new TestHandler();
        testHandler.setLevel(Level.WARNING);
        logger.addHandler(testHandler);

        testHandler.flush();
    }

    @Test
    void testSimpleFromFile() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");

        ArticleSource source = new FileJsonSource("test/simple1.json");
        SimpleNewsResponseJsonParser parser = new SimpleNewsResponseJsonParser(source, logger);

        SimpleArticle one = parser.getArticle();

        assertNotNull(one, "Parsed article should not be null");
        assertEquals("New Discoveries in Space", one.getTitle(), "Title mismatch");
        assertEquals("Scientists have discovered a new exoplanet that may have conditions suitable for life.", one.getDescription(), "Description mismatch");

        LocalDateTime expectedDate = LocalDateTime.parse("2023-08-12 14:30:22.123456", dtf);
        assertEquals(expectedDate, one.getPublishedAt(), "Published date mismatch");

        assertEquals("https://www.sciencenews.org/articles/new-discoveries-space", one.getUrl(), "URL mismatch");
    }

    @Test
    void testGetNewsResponseFromMockedUrlSource() throws IOException {
        URLJsonSource mockSource = mock(URLJsonSource.class);

        String mockJsonResponse = """
            {
                "title": "New Discoveries in Space",
                "description": "Scientists have discovered a new exoplanet that may have conditions suitable for life.",
                "publishedAt": "2023-08-12 14:30:22.123456",
                "url": "https://www.sciencenews.org/articles/new-discoveries-space"
            }
            """;

        when(mockSource.getJsonString()).thenReturn(mockJsonResponse);

        SimpleNewsResponseJsonParser parser = new SimpleNewsResponseJsonParser(mockSource, logger);

        SimpleArticle article = parser.getArticle();

        assertNotNull(article, "Parsed article should not be null");
        assertEquals("New Discoveries in Space", article.getTitle(), "Title mismatch");
        assertEquals("Scientists have discovered a new exoplanet that may have conditions suitable for life.", article.getDescription(), "Description mismatch");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime expectedDate = LocalDateTime.parse("2023-08-12 14:30:22.123456", dtf);
        assertEquals(expectedDate, article.getPublishedAt(), "Published date mismatch");
        assertEquals("https://www.sciencenews.org/articles/new-discoveries-space", article.getUrl(), "URL mismatch");
    }

    /**
     * Tests handling of a JSON article with a missing publication date.
     * Verifies that an error message is logged due to the missing field.
     */
    @Test
    void testSimpleNoDate() {
        ArticleSource source = new FileJsonSource("test/simpleNoDate.json");
        SimpleNewsResponseJsonParser parser = new SimpleNewsResponseJsonParser(source, logger);

        parser.getArticle();

        boolean isErrorLogged = testHandler.getMessages().stream()
                .anyMatch(t -> t.contains("Invalid"));
        assertTrue(isErrorLogged);
    }

    /**
     * Tests handling of a JSON article with a missing description.
     * Verifies that an error message is logged due to the missing field.
     */
    @Test
    void testSimpleNoDesc() {
        ArticleSource source = new FileJsonSource("test/simpleNoDesc.json");
        SimpleNewsResponseJsonParser parser = new SimpleNewsResponseJsonParser(source, logger);

        parser.getArticle();

        boolean isErrorLogged = testHandler.getMessages().stream()
                .anyMatch(t -> t.contains("Invalid"));
        assertTrue(isErrorLogged);
    }

    /**
     * Tests handling of a JSON article with missing title and URL fields.
     * Verifies that an error message is logged due to the missing fields.
     */
    @Test
    public void testSimpleNoTitleUrl() {
        ArticleSource source = new FileJsonSource("test/simpleNoTitleUrl.json");
        SimpleNewsResponseJsonParser parser = new SimpleNewsResponseJsonParser(source, logger);

        parser.getArticle();

        boolean isErrorLogged = testHandler.getMessages().stream()
                .anyMatch(t -> t.contains("Invalid"));
        assertTrue(isErrorLogged);
    }

}