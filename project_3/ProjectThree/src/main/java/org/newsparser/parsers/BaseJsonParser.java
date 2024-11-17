package org.newsparser.parsers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.newsparser.sources.ArticleSource;
import org.newsparser.sources.SourceFormat;

import java.util.logging.Logger;

/**
 * Abstract base class for JSON parsers, providing shared functionality and state.
 * This class includes an {@link ObjectMapper} for JSON processing, an {@link ArticleSource}
 * representing the data source, and a {@link Logger} for logging activities.
 */
public abstract class BaseJsonParser {

    /**
     * An {@link ObjectMapper} instance for JSON processing.
     * This can be customized or replaced by the specific parser implementation.
     */
    protected ObjectMapper objectMapper;

    /**
     * The {@link ArticleSource} representing the data source for the JSON content.
     * This can be updated or replaced during parsing.
     */
    protected ArticleSource dataSource;

    /**
     * A {@link Logger} instance for logging messages, warnings, or errors.
     */
    protected final Logger logger;

    /**
     * Constructs a new {@code BaseJsonParser} with the specified data source and logger.
     * Automatically registers the {@link JavaTimeModule} with the {@link ObjectMapper}
     * to handle Java time types (e.g., LocalDate, LocalDateTime) during JSON processing.
     *
     * @param dataSource the {@link ArticleSource} providing the JSON string for parsing
     * @param logger     the {@link Logger} for logging information and warnings
     */
    protected BaseJsonParser(ArticleSource dataSource, Logger logger) {
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
        this.dataSource = dataSource;
        this.logger = logger;
    }

    /**
     * Gets the {@link ObjectMapper} used for JSON processing.
     *
     * @return the {@link ObjectMapper} instance
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * Sets the {@link ObjectMapper} used for JSON processing.
     *
     * @param objectMapper the {@link ObjectMapper} instance to set
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Gets the {@link ArticleSource} representing the current data source.
     *
     * @return the {@link ArticleSource} instance
     */
    public ArticleSource getDataSource() {
        return dataSource;
    }

    /**
     * Sets the {@link ArticleSource} representing the data source.
     *
     * @param dataSource the {@link ArticleSource} instance to set
     */
    public void setDataSource(ArticleSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Abstract method to process the JSON data.
     * Implementations should define how the JSON content is processed.
     */
    public abstract void visit();
}