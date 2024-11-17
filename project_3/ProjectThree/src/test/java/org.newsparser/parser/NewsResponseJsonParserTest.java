package org.newsparser.parser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.newsparser.TestHandler;
import org.newsparser.databind.Article;
import org.newsparser.parsers.NewsResponseJsonParser;
import org.newsparser.sources.ArticleSource;
import org.newsparser.sources.FileJsonSource;
import org.newsparser.sources.URLJsonSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewsResponseJsonParserTest {

    private Logger logger;
    private TestHandler testHandler;

    /**
     * Sets up the logger and test handler before each test.
     * Configures a custom {@link TestHandler} to capture log messages.
     */
    @BeforeEach
    public void setUp() {
        logger = Logger.getLogger(NewsResponseJsonParserTest.class.getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

        testHandler = new TestHandler();
        testHandler.setLevel(Level.WARNING);
        logger.addHandler(testHandler);

        testHandler.flush();
    }

    /**
     * Tests the parsing of articles from a mocked {@link URLJsonSource}.
     * Verifies that the parser correctly processes JSON response data and extracts articles.
     *
     * @throws ParseException if there is an error parsing date formats.
     * @throws IOException    if there is an I/O error during test setup.
     */
    @Test
    public void testGetNewsResponseFromUrlSource() throws ParseException, IOException {
        URLJsonSource mockSource = mock(URLJsonSource.class);

        String mockJsonResponse = """
            {
                "status": "ok",
                "totalResults": 2,
                "articles": [
                    {
                        "title": "Test Article One",
                        "description": "Description One",
                        "publishedAt": "2021-12-18T12:32:00Z",
                        "url": "www.testone.com"
                    },
                    {
                        "title": "Test Article Two",
                        "description": "Description Two",
                        "publishedAt": "2021-12-19T13:32:00Z",
                        "url": "www.testtwo.com"
                    }
                ]
            }
            """;

        when(mockSource.getJsonString()).thenReturn(mockJsonResponse);

        NewsResponseJsonParser parser = new NewsResponseJsonParser(mockSource, logger);
        List<Article> articles = parser.getNewsResponse().getArticles();

        assertEquals(2, articles.size());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

        Article one = articles.get(0);
        assertEquals("Test Article One", one.getTitle());
        assertEquals("Description One", one.getDescription());
        assertEquals(sdf.parse("2021-12-18T12:32:00Z"), one.getPublishedDateTime());
        assertEquals("www.testone.com", one.getUrl());

        Article two = articles.get(1);
        assertEquals("Test Article Two", two.getTitle());
        assertEquals("Description Two", two.getDescription());
        assertEquals(sdf.parse("2021-12-19T13:32:00Z"), two.getPublishedDateTime());
        assertEquals("www.testtwo.com", two.getUrl());
    }

    /**
     * Tests the parsing of articles from a {@link FileJsonSource}.
     * Validates that the parser reads articles from a JSON file and parses them correctly.
     *
     * @throws ParseException if there is an error parsing date formats.
     */
    @Test
    public void testGetNewsResponseFromFile() throws ParseException {
        Logger logger = Logger.getLogger(NewsResponseJsonParserTest.class.getName());
        logger.setLevel(Level.WARNING);
        logger.setUseParentHandlers(false);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.WARNING);
        logger.addHandler(consoleHandler);

        ArticleSource source = new FileJsonSource("test/testGetArticlesFromFile.json");
        NewsResponseJsonParser parser = new NewsResponseJsonParser(source, logger);
        List<Article> articles = parser.getNewsResponse().getArticles();

        Article one = articles.get(0);
        Article two = articles.get(1);

        assertEquals(2, articles.size());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

        assertEquals("Test Article One", one.getTitle());
        assertEquals("Description One", one.getDescription());
        assertEquals(sdf.parse("2021-12-18T12:32:00Z"), one.getPublishedDateTime());
        assertEquals("www.testone.com", one.getUrl());

        assertEquals("Test Article Two", two.getTitle());
        assertEquals("Description Two", two.getDescription());
        assertEquals(sdf.parse("2021-12-19T13:32:00Z"), two.getPublishedDateTime());
        assertEquals("www.testtwo.com", two.getUrl());
    }

    /**
     * Tests the handling of JSON responses with no articles.
     * Verifies that an error is logged when the JSON response contains no articles.
     *
     * @throws ParseException if there is an error parsing date formats.
     */
    @Test
    public void testGetJsonWithNoArticles() throws ParseException {
        ArticleSource source = new FileJsonSource("test/testJsonNoArticles.json");
        NewsResponseJsonParser parser = new NewsResponseJsonParser(source, logger);
        parser.getNewsResponse();

        boolean isErrorLogged = testHandler.getMessages().stream()
                .anyMatch(t -> t.contains("Invalid JSON response from data source."));
        assertTrue(isErrorLogged);
    }

    /**
     * Tests the handling of articles with missing fields in the JSON response.
     * Verifies that the parser logs an error for each invalid article and skips it.
     * Some articles missing one field, some missing multiple.
     *
     * @throws ParseException if there is an error parsing date formats.
     */
    @Test
    public void testHandleSomeBadArticles() throws ParseException {
        ArticleSource source = new FileJsonSource("test/someArticlesMissingFields.json");
        NewsResponseJsonParser parser = new NewsResponseJsonParser(source, logger);
        List<Article> articles = parser.getNewsResponse().getArticles();

        long invalidArticleCount = testHandler.getMessages().stream()
                .filter(t -> t.contains("Invalid"))
                .count();

        assertEquals(6, invalidArticleCount, "Expected 6 'Invalid article skipped' log entries");
        assertEquals(0, articles.size());
    }
}