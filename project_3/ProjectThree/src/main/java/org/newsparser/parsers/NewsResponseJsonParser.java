package org.newsparser.parsers;

import org.newsparser.databind.Article;
import org.newsparser.databind.NewsResponse;
import org.newsparser.sources.ArticleSource;
import org.newsparser.utils.Validator;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * A JSON parser specifically for processing and validating news responses.
 * This parser reads JSON data from an {@link ArticleSource}, converts it into
 * a {@link NewsResponse} object, and validates the response and its articles.
 */
public class NewsResponseJsonParser extends BaseJsonParser {

    /**
     * Constructs a {@code NewsResponseJsonParser} with the specified data source and logger.
     *
     * @param dataSource the {@link ArticleSource} providing the JSON string of articles
     * @param logger     the {@link Logger} for logging information and warnings
     */
    public NewsResponseJsonParser(ArticleSource dataSource, Logger logger) {
        super(dataSource, logger);
    }

    /**
     * Parses the JSON string from the data source into a {@link NewsResponse} object.
     * This method validates the entire response and each individual article.
     * Invalid articles are filtered out, and only valid articles are included in the result.
     *
     * <p>If the JSON parsing fails or the response is invalid, an empty error response
     * is returned, and a warning is logged.</p>
     *
     * @return a {@link NewsResponse} containing only valid articles,
     *         or an empty error response if parsing fails or the data is invalid
     */
    public NewsResponse getNewsResponse() {
        try {
            // Parse the JSON string into a NewsResponse object
            NewsResponse newsResponse = objectMapper.readValue(dataSource.getJsonString(), NewsResponse.class);

            // Validate the NewsResponse object
            if (!newsResponse.isValid()) {
                logger.warning("Invalid JSON response from data source.");
                return null;
            }

            // Filter valid articles from the response
            List<Article> validArticles = Validator.filterValidItems(newsResponse.getArticles(), logger);

            // Return a new NewsResponse with only valid articles
            return new NewsResponse(newsResponse.getStatus(), validArticles.size(), validArticles);

        } catch (IOException e) {
            // Handle JSON parsing errors
            logger.warning("Failed to parse JSON data: " + e.getMessage());
            return new NewsResponse("error", 0, List.of());
        }
    }

    /**
     * Processes the JSON data and displays the valid articles.
     * This method retrieves the {@link NewsResponse} from the JSON source
     * and calls {@link NewsResponse#displayArticles()} to display them.
     */
    @Override
    public void visit() {
        NewsResponse newsResponse = getNewsResponse();
        if (newsResponse != null) {
            newsResponse.displayArticles();
        }
    }
}
