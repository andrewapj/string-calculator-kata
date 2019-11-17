package com.github.andrewapj;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.github.andrewapj.splitter.CommaStringSplitter;
import com.github.andrewapj.splitter.LineStringSplitter;
import com.github.andrewapj.splitter.StringSplitter;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StringCalculatorTest {

    @Mock private StringSplitter mockSplitter = new StringSplitter(new CommaStringSplitter(),
        new LineStringSplitter());

    @Before
    public void setup() {

        when(mockSplitter.split("10"))
            .thenReturn(Stream.of(10).collect(Collectors.toList()));

        when(mockSplitter.split("1,5"))
            .thenReturn(Stream.of(1,5).collect(Collectors.toList()));

        when(mockSplitter.split("1" + System.lineSeparator() +"10"))
            .thenReturn(Stream.of(1,10).collect(Collectors.toList()));

        when(mockSplitter.split("5,5" + System.lineSeparator() + "10"))
            .thenReturn(Stream.of(5,5,10).collect(Collectors.toList()));

        when(mockSplitter.split("10,-1"))
            .thenThrow(new NumberFormatException());

        when(mockSplitter.split("9,5000, 1"))
            .thenReturn(Stream.of(9,1).collect(Collectors.toList()));
    }

    @Test
    public void should_GetZeroFromEmptyString() {
        assertEquals(0, new StringCalculator(mockSplitter).calculate(""));
    }

    @Test
    public void should_GetValueFromSingleValueInString() {
        assertEquals(10, new StringCalculator(mockSplitter).calculate("10"));
    }

    @Test
    public void should_GetSumFromTwoNumbers_WithCommaDelimiter() {
        assertEquals(6, new StringCalculator(mockSplitter).calculate("1,5"));
    }

    @Test
    public void should_GetSumFromTwoNumbers_WithNewlineDelimiter() {
        assertEquals(11, new StringCalculator(mockSplitter)
            .calculate("1" + System.lineSeparator() + "10"));
    }

    @Test
    public void should_GetThreeNumbers_WithCommaAndNewLineDelimiter() {
        assertEquals(20, new StringCalculator(mockSplitter)
            .calculate("5,5" + System.lineSeparator() + "10"));
    }

    @Test(expected = NumberFormatException.class)
    public void should_GetExceptionFromNegativeNumber() {
        new StringCalculator(mockSplitter).calculate("10,-1");
    }

    @Test
    public void should_IgnoreNumbersGreaterThen1000() {
        assertEquals(10, new StringCalculator(mockSplitter)
            .calculate("9,5000, 1"));
    }
}
