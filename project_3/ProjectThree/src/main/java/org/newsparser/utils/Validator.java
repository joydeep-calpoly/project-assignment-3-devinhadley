package org.newsparser.utils;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Utility class for validation purposes, providing functionality to filter out
 * invalid items from a list while logging any invalid entries.
 */
public class Validator {

    // Private constructor to prevent instantiation
    private Validator() {}

    /**
     * Filters a list of items, retaining only those that pass validation.
     * Invalid items are logged and skipped from the resulting list.
     *
     * @param items  the list of items to be validated and filtered
     * @param logger the logger to log invalid items
     * @param <T>    the type of items in the list, which must implement {@link Validatable}
     * @return a list of valid items, with invalid items filtered out
     */
    public static <T extends Validatable> List<T> filterValidItems(List<T> items, Logger logger) {
        return items.stream()
                .filter(item -> isItemValid(item, logger))
                .collect(Collectors.toList());
    }

    /**
     * Checks if a single item is valid based on its {@link Validatable#isValid()} method.
     * Logs a warning if the item is invalid.
     *
     * @param item   the item to validate
     * @param logger the logger to log the invalid item if validation fails
     * @param <T>    the type of the item, which must implement {@link Validatable}
     * @return true if the item is valid, otherwise false
     */
    public static <T extends Validatable> boolean isItemValid(T item, Logger logger) {
        if (!item.isValid()) {
            logger.warning("Invalid item: " + item);
            return false;
        }
        return true;
    }
}