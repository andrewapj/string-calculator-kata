package com.github.andrewapj.splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Splits a line break delimited string.
 */
public class LineStringSplitter {

    /**
     * Splits a string by a new line.
     *
     * @param delimitedString       the string to split.
     * @return                      the elements that were split.
     */
    List<String> split(String delimitedString) {
        if (delimitedString == null || delimitedString.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return Arrays.stream(delimitedString.split(System.lineSeparator()))
                .collect(Collectors.toList());
        }
    }
}
