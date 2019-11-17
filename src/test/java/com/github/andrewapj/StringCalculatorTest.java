package com.github.andrewapj;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void should_GetZeroFromEmptyString() {
        assertEquals(0, new StringCalculator().calculate(""));
    }

    @Test
    public void should_GetValueFromSingleValueInString() {
        assertEquals(10, new StringCalculator().calculate("10"));

    }
}
