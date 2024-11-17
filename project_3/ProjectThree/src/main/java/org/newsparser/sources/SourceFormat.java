package org.newsparser.sources;

import org.newsparser.parsers.BaseJsonParser;
import org.newsparser.parsers.NewsResponseJsonParser;
import org.newsparser.parsers.SimpleNewsResponseJsonParser;

/**
 * Represents the format of an article source and its associated data format.
 * This class manages the type of source and format for articles,
 * ensuring proper parser validation and compatibility.
 */
public class SourceFormat {

    /**
     * Enum representing the format type of an article.
     */
    public enum ArticleFormatType {
        /** Represents the NewsResponse format for articles. */
        NEWS_RESPONSE,

        /** Represents a simple article format. */
        SIMPLE_ARTICLE
    }

    /**
     * Enum representing the source type of an article.
     */
    public enum ArticleSourceType {
        /** Represents a web-based article source. */
        WEB,

        /** Represents a file-based JSON article source. */
        FILE_JSON
    }

    private final ArticleSourceType articleSourceType;
    private final ArticleFormatType articleFormatType;

    /**
     * Constructs a new {@code SourceFormat} with the specified source type and format type.
     *
     * @param articleSourceType the source type of the article (e.g., WEB or FILE_JSON)
     * @param articleFormatType the format type of the article (e.g., NEWS_RESPONSE or SIMPLE_ARTICLE)
     */
    public SourceFormat(ArticleSourceType articleSourceType, ArticleFormatType articleFormatType) {
        this.articleSourceType = articleSourceType;
        this.articleFormatType = articleFormatType;
    }

    /**
     * Validates and accepts a {@link BaseJsonParser} for the specified source and format type.
     *
     * @param parser the {@link BaseJsonParser} instance to be validated and processed
     * @throws RuntimeException if the provided parser is incompatible with the format type
     */
    public void accept(BaseJsonParser parser) {
        if (ArticleFormatType.NEWS_RESPONSE == articleFormatType) {

            if (parser.getClass() != NewsResponseJsonParser.class) {
                throw new RuntimeException("Invalid parser used for NewsResponse format.");
            }
        } else {
            if (parser.getClass() != SimpleNewsResponseJsonParser.class) {
                throw new RuntimeException("Invalid parser used for Simple format.");
            }
            if (ArticleSourceType.WEB == articleSourceType) {
                throw new RuntimeException("Invalid source URLJsonSource used for SimpleArticle format.");
            }
        }

        parser.visit();
    }

    /**
     * Gets the source type of the article.
     *
     * @return the source type of the article
     */
    public ArticleSourceType getArticleSourceType() {
        return articleSourceType;
    }

    /**
     * Gets the format type of the article.
     *
     * @return the format type of the article
     */
    public ArticleFormatType getArticleFormatType() {
        return articleFormatType;
    }
}