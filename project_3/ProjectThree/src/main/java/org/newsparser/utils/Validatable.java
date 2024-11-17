package org.newsparser.utils;

/**
 * Interface for objects that can be validated.
 * Classes implementing this interface should define criteria for validation.
 */
public interface Validatable {

    /**
     * Determines whether the object is valid based on specific criteria.
     *
     * @return true if the object is valid, false otherwise
     */
    boolean isValid();
}
