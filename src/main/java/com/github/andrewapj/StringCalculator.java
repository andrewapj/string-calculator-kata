package com.github.andrewapj;

import com.github.andrewapj.splitter.StringSplitter;

/**
 * Calculator to process an delimited string and calculate the sum if its values.
 */
class StringCalculator {

    private final StringSplitter splitter;

    StringCalculator(StringSplitter splitter) {
        this.splitter = splitter;
    }

    /**
     * Calculates the sum of the values from a delimited String.
     *
     * @param delimitedString       the delimited string with integer values to be added up.
     * @return                      the sum of the values.
     */
    int calculate(final String delimitedString) throws NumberFormatException{

        if (delimitedString == null || delimitedString.isEmpty()){
            return 0;
        }
        else {
            return splitter.split(delimitedString).stream()
                .mapToInt(value -> value)
                .sum();
        }
    }
}
