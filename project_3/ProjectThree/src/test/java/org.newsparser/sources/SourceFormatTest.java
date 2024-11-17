package org.newsparser.sources;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.newsparser.parsers.BaseJsonParser;
import org.newsparser.parsers.NewsResponseJsonParser;
import org.newsparser.parsers.SimpleNewsResponseJsonParser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the {@link SourceFormat} class.
 */
class SourceFormatTest {

    /**
     * Tests that a {@link NewsResponseJsonParser} correctly processes
     * a {@link SourceFormat} with type WEB and NEWS_RESPONSE.
     */
    @Test
    void testAcceptWithNewsResponseFromWeb() {
        SourceFormat.ArticleSourceType sourceType = SourceFormat.ArticleSourceType.WEB;
        SourceFormat.ArticleFormatType formatType = SourceFormat.ArticleFormatType.NEWS_RESPONSE;
        SourceFormat sourceFormat = new SourceFormat(sourceType, formatType);

        NewsResponseJsonParser mockParser = Mockito.mock(NewsResponseJsonParser.class);

        sourceFormat.accept(mockParser);

        // Visit should be called once!
        verify(mockParser, times(1)).visit();
    }

    /**
     * Tests that a {@link NewsResponseJsonParser} correctly processes
     * a {@link SourceFormat} with type FILE_JSON and NEWS_RESPONSE.
     */
    @Test
    void testAcceptWithNewsResponseFromFile() {
        SourceFormat.ArticleSourceType sourceType = SourceFormat.ArticleSourceType.FILE_JSON;
        SourceFormat.ArticleFormatType formatType = SourceFormat.ArticleFormatType.NEWS_RESPONSE;
        SourceFormat sourceFormat = new SourceFormat(sourceType, formatType);

        NewsResponseJsonParser mockParser = Mockito.mock(NewsResponseJsonParser.class);

        sourceFormat.accept(mockParser);

        verify(mockParser, times(1)).visit();
    }

    /**
     * Tests that a {@link SimpleNewsResponseJsonParser} correctly processes
     * a {@link SourceFormat} with type FILE_JSON and SIMPLE_ARTICLE.
     */
    @Test
    void testAcceptWithSimpleArticleFromFile() {
        SourceFormat.ArticleSourceType sourceType = SourceFormat.ArticleSourceType.FILE_JSON;
        SourceFormat.ArticleFormatType formatType = SourceFormat.ArticleFormatType.SIMPLE_ARTICLE;
        SourceFormat sourceFormat = new SourceFormat(sourceType, formatType);

        SimpleNewsResponseJsonParser mockParser = Mockito.mock(SimpleNewsResponseJsonParser.class);

        sourceFormat.accept(mockParser);

        verify(mockParser, times(1)).visit();
    }

    /**
     * Tests that an invalid combination of WEB source type and SIMPLE_ARTICLE format type
     * throws a {@link RuntimeException}.
     */
    @Test
    void testAcceptWithInvalidSourceForSimpleArticle() {
        SourceFormat.ArticleSourceType sourceType = SourceFormat.ArticleSourceType.WEB;
        SourceFormat.ArticleFormatType formatType = SourceFormat.ArticleFormatType.SIMPLE_ARTICLE;
        SourceFormat sourceFormat = new SourceFormat(sourceType, formatType);

        SimpleNewsResponseJsonParser mockParser = Mockito.mock(SimpleNewsResponseJsonParser.class);

        assertThrows(RuntimeException.class, () -> sourceFormat.accept(mockParser));
        verify(mockParser, never()).visit();
    }

    /**
     * Tests that using a {@link SimpleNewsResponseJsonParser} for a NEWS_RESPONSE
     * format type throws a {@link RuntimeException}.
     */
    @Test
    void testAcceptWithWrongParserForNewsResponse() {
        SourceFormat.ArticleSourceType sourceType = SourceFormat.ArticleSourceType.FILE_JSON;
        SourceFormat.ArticleFormatType formatType = SourceFormat.ArticleFormatType.NEWS_RESPONSE;
        SourceFormat sourceFormat = new SourceFormat(sourceType, formatType);

        SimpleNewsResponseJsonParser mockParser = Mockito.mock(SimpleNewsResponseJsonParser.class);

        assertThrows(RuntimeException.class, () -> sourceFormat.accept(mockParser));
        verify(mockParser, never()).visit();
    }

    /**
     * Tests that using a {@link NewsResponseJsonParser} for a SIMPLE_ARTICLE
     * format type throws a {@link RuntimeException}.
     */
    @Test
    void testAcceptWithWrongParserForSimpleArticle() {
        SourceFormat.ArticleSourceType sourceType = SourceFormat.ArticleSourceType.FILE_JSON;
        SourceFormat.ArticleFormatType formatType = SourceFormat.ArticleFormatType.SIMPLE_ARTICLE;
        SourceFormat sourceFormat = new SourceFormat(sourceType, formatType);

        NewsResponseJsonParser mockParser = Mockito.mock(NewsResponseJsonParser.class);

        assertThrows(RuntimeException.class, () -> sourceFormat.accept(mockParser));
        verify(mockParser, never()).visit();
    }

    /**
     * Tests that an unrelated {@link BaseJsonParser} for a NEWS_RESPONSE
     * format type throws a {@link RuntimeException}.
     */
    @Test
    void testAcceptWithUnrelatedParserForNewsResponse() {
        SourceFormat.ArticleSourceType sourceType = SourceFormat.ArticleSourceType.WEB;
        SourceFormat.ArticleFormatType formatType = SourceFormat.ArticleFormatType.NEWS_RESPONSE;
        SourceFormat sourceFormat = new SourceFormat(sourceType, formatType);

        BaseJsonParser unrelatedParser = Mockito.mock(BaseJsonParser.class);

        assertThrows(RuntimeException.class, () -> sourceFormat.accept(unrelatedParser));
        verify(unrelatedParser, never()).visit();
    }

    /**
     * Tests that an unrelated {@link BaseJsonParser} for a SIMPLE_ARTICLE
     * format type throws a {@link RuntimeException}.
     */
    @Test
    void testAcceptWithUnrelatedParserForSimpleArticle() {
        SourceFormat.ArticleSourceType sourceType = SourceFormat.ArticleSourceType.FILE_JSON;
        SourceFormat.ArticleFormatType formatType = SourceFormat.ArticleFormatType.SIMPLE_ARTICLE;
        SourceFormat sourceFormat = new SourceFormat(sourceType, formatType);

        BaseJsonParser unrelatedParser = Mockito.mock(BaseJsonParser.class);

        assertThrows(RuntimeException.class, () -> sourceFormat.accept(unrelatedParser));
        verify(unrelatedParser, never()).visit();
    }
}
