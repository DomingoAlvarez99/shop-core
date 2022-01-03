package org.dalvarez.ddd_example.shared.domain.criteria.filter;

public class WrongFilterException extends IllegalArgumentException {

    private static final String FORMAT_MESSAGE = "The filter <%s> could not be processed, correct format <%s>";

    public WrongFilterException(final String filter) {
        super(String.format(FORMAT_MESSAGE, filter, Filter.FILTER_REGEX));
    }

}
