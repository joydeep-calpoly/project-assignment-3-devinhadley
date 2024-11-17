package org.newsparser.parsers;

import org.newsparser.databind.Article;
import org.newsparser.databind.NewsResponse;
import org.newsparser.databind.SimpleArticle;
import org.newsparser.sources.ArticleSource;
import org.newsparser.utils.Validator;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * A JSON parser specifically for processing and validating {@link SimpleArticle} objects.
 * This parser reads JSON data from an {@link ArticleSource}, converts it into a {@link SimpleArticle},
 * and validates the parsed article.
 */
public class SimpleNewsResponseJsonParser extends BaseJsonParser {

    /**
     * Constructs a {@code SimpleNewsResponseJsonParser} with the specified data source and logger.
     *
     * @param dataSource the {@link ArticleSource} providing the JSON string for the article
     * @param logger     the {@link Logger} for logging parsing information and warnings
     */
    public SimpleNewsResponseJsonParser(ArticleSource dataSource, Logger logger) {
        super(dataSource, logger);
    }

    /**
     * Processes the JSON data and displays the parsed {@link SimpleArticle}.
     * This method retrieves the {@link SimpleArticle} from the JSON source
     * and calls {@link SimpleArticle#display()} to present it.
     */
    @Override
    public void visit() {
        SimpleArticle article = getArticle();
        if (article != null) {
            article.display();
        } else {
            logger.warning("No valid article to display.");
        }
    }

    /**
     * Parses the JSON string from the data source into a {@link SimpleArticle} object.
     * This method validates the article after parsing. If the parsed article is invalid
     * or if JSON parsing fails, it logs an error and returns {@code null}.
     *
     * @return a valid {@link SimpleArticle} if parsing and validation succeed;
     *         otherwise, returns {@code null}
     */
    public SimpleArticle getArticle() {
        try {
            // Parse the JSON string into a SimpleArticle object
            SimpleArticle article = objectMapper.readValue(dataSource.getJsonString(), SimpleArticle.class);

            // Validate the parsed article
            if (Validator.isItemValid(article, logger)) {
                return article;
            }

            logger.warning("Parsed article is invalid.");
            return null;

        } catch (IOException e) {
            // Handle JSON parsing errors
            logger.warning("Failed to parse JSON data into SimpleArticle: " + e.getMessage());
            return null;
        }
    }
}
