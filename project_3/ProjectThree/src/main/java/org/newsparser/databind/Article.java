package org.newsparser.databind;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.newsparser.utils.Validatable;

import java.util.Date;

/**
 * Represents a news article with metadata such as title, description, author,
 * source, and publication details. Implements {@link Validatable} for validation.
 */
public class Article implements Validatable {

    private final Source source;
    private final String author;
    private final String title;
    private final String description;
    private final String url;
    private final String urlToImage;
    private final Date publishedDateTime;
    private final String content;

    /**
     * Constructs an Article instance with the specified properties.
     *
     * @param source             the source of the article
     * @param author             the author of the article
     * @param title              the title of the article
     * @param description        a brief description of the article
     * @param url                the URL of the article
     * @param urlToImage         the URL to an image representing the article
     * @param publishedDateTime  the publication date and time of the article
     * @param content            the content of the article
     */
    @JsonCreator
    public Article(
            @JsonProperty("source") Source source,
            @JsonProperty("author") String author,
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("url") String url,
            @JsonProperty("urlToImage") String urlToImage,
            @JsonProperty("publishedAt")
            @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
            Date publishedDateTime,
            @JsonProperty("content") String content) {
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.urlToImage = urlToImage;
        this.publishedDateTime = (publishedDateTime != null) ? new Date(publishedDateTime.getTime()) : null;
        this.content = content;
    }

    /**
     * Validates that all required fields are present and non-empty.
     * Articles that are not valid will be filtered out in parser.
     *
     * @return true if the article is valid, otherwise false
     */
    @Override
    public boolean isValid() {
        return  title != null && !title.trim().isEmpty() &&
                description != null && !description.trim().isEmpty() &&
                url != null && !url.trim().isEmpty() &&
                publishedDateTime != null;
    }

    /**
     * Gets the source of the article.
     *
     * @return the source object of the article
     */
    public Source getSource() {
        return source;
    }

    /**
     * Gets the author of the article.
     *
     * @return the author's name, or null if unspecified
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Gets the title of the article.
     *
     * @return the title of the article
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets a brief description of the article.
     *
     * @return the description of the article
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the URL of the article.
     *
     * @return the URL of the article
     */
    public String getUrl() {
        return url;
    }

    /**
     * Gets the URL to an image representing the article.
     *
     * @return the image URL, or null if not available
     */
    public String getUrlToImage() {
        return urlToImage;
    }

    /**
     * Gets the publication date and time of the article.
     *
     * @return a copy of the publication date and time, or null if not set
     */
    public Date getPublishedDateTime() {
        return (publishedDateTime != null) ? new Date(publishedDateTime.getTime()) : null;
    }

    /**
     * Gets the content of the article.
     *
     * @return the full content of the article
     */
    public String getContent() {
        return content;
    }

    /**
     * Provides a string representation of the article, including its
     * source, author, title, description, URL, publication date, and content.
     *
     * @return a string representation of the article
     */
    @Override
    public String toString() {
        return "\n" +
                "  Source Id='" + (source != null ? source.getId() : "null") + "',\n" +
                "  Source Name='" + (source != null ? source.getName() : "null") + "',\n" +
                "  Author='" + author + "',\n" +
                "  Title='" + title + "',\n" +
                "  Description='" + description + "',\n" +
                "  URL='" + url + "',\n" +
                "  URL to Image='" + urlToImage + "',\n" +
                "  Published DateTime=" + publishedDateTime + ",\n" +
                "  Content='" + content + "'\n";
    }
}
