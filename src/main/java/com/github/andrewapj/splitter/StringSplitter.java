package com.github.andrewapj.splitter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Splits a String by a comma and a new line returns a list of {@link Integer} found in the string.
 */
public class StringSplitter {

    private static final int MAX_ALLOWED_VALUE = 1000;
    private final CommaStringSplitter commaStringSplitter;
    private final LineStringSplitter lineStringSplitter;

    public StringSplitter(CommaStringSplitter commaStringSplitter, LineStringSplitter lineStringSplitter) {
        this.commaStringSplitter = commaStringSplitter;
        this.lineStringSplitter = lineStringSplitter;
    }

    /**
     * Splits the string and returns the integers found in the string.
     *
     * @param delimitedString       the string to split.
     * @return                      the integers found.
     * @throws NumberFormatException if the number can not be parsed or is negative.
     */
    public List<Integer> split(final String delimitedString) {

        try {
            return Stream.of(Integer.parseInt(delimitedString)).collect(Collectors.toList());
        }
        catch (NumberFormatException ex) {

            return commaStringSplitter.split(delimitedString).stream()
                .map(lineStringSplitter::split)
                .flatMap(List::stream)
                .map(Integer::parseInt)
                .filter(integer -> integer <= MAX_ALLOWED_VALUE)
                .map(this::checkForNegative)
                .collect(Collectors.toList());
        }
    }

    private int checkForNegative(final int number) {
        if (number < 0){
            throw new NumberFormatException();
        }
        return number;
    }

}
