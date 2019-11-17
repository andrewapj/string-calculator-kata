package com.github.andrewapj;

/**
 * Calculator to process an delimited string and calculate the sum if its values.
 */
public class StringCalculator {

    /**
     * Calculates the sum of the values from a delimited String.
     *
     * @param delimitedString       the delimited string with integer values to be added up.
     * @return                      the sum of the values.
     */
    public int calculate(final String delimitedString) throws NumberFormatException{

        if (delimitedString == null || delimitedString.isEmpty()){
            return 0;
        }
        else {
            return Integer.parseInt(delimitedString);
        }
    }
}
