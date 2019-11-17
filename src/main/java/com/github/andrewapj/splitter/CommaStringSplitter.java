package com.github.andrewapj.splitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Splits a comma delimited string.
 */
public class CommaStringSplitter {

    /**
     * Splits a comma delimited string.
     *
     * @param delimitedString       the string to split
     * @return                      a list of elements that were split.
     */
    List<String> split(String delimitedString) {
        if (delimitedString == null || delimitedString.isEmpty()) {
            return new ArrayList<>();
        }
        else {
            return Arrays.stream(delimitedString.split(","))
                .collect(Collectors.toList());
        }
    }
}
