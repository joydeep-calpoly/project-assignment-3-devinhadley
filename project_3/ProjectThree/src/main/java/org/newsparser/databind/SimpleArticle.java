package org.newsparser.databind;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.newsparser.utils.Validatable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a simplified version of an article containing essential information such as title,
 * description, publication date, and URL. Implements {@link Validatable} to ensure validation of required fields.
 */
public class SimpleArticle implements Validatable {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("publishedAt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    private LocalDateTime publishedAt;

    @JsonProperty("url")
    private String url;

    /**
     * Default constructor for SimpleArticle.
     */
    public SimpleArticle() {
    }

    /**
     * Constructs a SimpleArticle instance with the specified title, description, publication date, and URL.
     * The publication date is parsed from a string format.
     *
     * @param title       the title of the article
     * @param description a brief description of the article
     * @param publishedAt the publication date and time as a string, formatted as "yyyy-MM-dd HH:mm:ss.SSSSSS"
     * @param url         the URL of the article
     */
    public SimpleArticle(String title, String description, String publishedAt, String url) {
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt != null ? LocalDateTime.parse(publishedAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")) : null;
        this.url = url;
    }

    /**
     * Checks if the SimpleArticle object is valid.
     * A valid SimpleArticle must have non-null, non-empty values for title, description, publication date, and URL.
     *
     * @return true if the SimpleArticle is valid, otherwise false
     */
    @Override
    public boolean isValid() {
        return title != null && !title.isEmpty() && description != null && !description.isEmpty() && publishedAt != null && url != null && !url.isEmpty();
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
     * Gets the description of the article.
     *
     * @return the description of the article
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the publication date and time of the article.
     *
     * @return the publication date and time as a LocalDateTime object
     */
    public LocalDateTime getPublishedAt() {
        return publishedAt;
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
     * Returns a string representation of the SimpleArticle object, including title,
     * publication date, description, and URL.
     *
     * @return a string representation of the SimpleArticle
     */
    @Override
    public String toString() {
        return "Title: " + title + "\n" + "Published At: " + publishedAt + "\n" + "Description: " + description + "\n" + "URL: " + url + "\n";
    }

    public void display(){
        System.out.println("Title: " + this.getTitle());
        System.out.println("Description: " + this.getDescription());
        System.out.println("Published Date: " + this.getPublishedAt());
        System.out.println("URL: " + this.getUrl());
    }
}
