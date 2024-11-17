package org.newsparser.databind;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.newsparser.utils.Validatable;

/**
 * Represents the source of a news article, containing essential information
 * such as the source's unique identifier and name.
 * Implements {@link Validatable} to allow validation of its fields.
 */
public class Source implements Validatable {

    private final String id;
    private final String name;

    /**
     * Constructs a Source instance with the specified id and name.
     *
     * @param id   the unique identifier of the source, typically provided by the API
     * @param name the name of the source (e.g., news agency or publication name)
     */
    @JsonCreator
    public Source(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the unique identifier of the source.
     *
     * @return the source's unique identifier, or null if not set
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of the source.
     *
     * @return the name of the source, or null if not set
     */
    public String getName() {
        return name;
    }

    /**
     * Provides a string representation of the source, including its ID and name.
     *
     * @return a string representation of the source, formatted as "id='ID', name='NAME'"
     */
    @Override
    public String toString() {
        return "id='" + id + "',\n" + "  name='" + name + "'\n";
    }

    /**
     * Validates that both the source ID and name are present and non-empty.
     *
     * @return true if both the id and name are non-null and non-empty, otherwise false
     */
    @Override
    public boolean isValid() {
        return id != null && !id.trim().isEmpty() && name != null && !name.trim().isEmpty();
    }
}