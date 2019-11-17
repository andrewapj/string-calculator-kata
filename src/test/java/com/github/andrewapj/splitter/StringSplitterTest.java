package com.github.andrewapj.splitter;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StringSplitterTest {

    @Mock private CommaStringSplitter mockCommaStringSplitter = new CommaStringSplitter();
    @Mock private LineStringSplitter mockLineStringSplitter = new LineStringSplitter();
    private StringSplitter splitter;

    @Before
    public void setup() {

        when(mockCommaStringSplitter.split(anyString())).thenAnswer(invocationOnMock -> {

            String arg = invocationOnMock.getArgument(0);
            if (arg != null && arg.equals("1,5")) {
                return Stream.of("1","5").collect(Collectors.toList());
            }
            else if (arg != null && arg.equals("9,5000,1")) {
                return Stream.of("9","1").collect(Collectors.toList());
            }
            else if (arg != null && arg.equals("10,-1")) {
                return Stream.of("10","-1").collect(Collectors.toList());
            }
            else if (arg != null && arg.equals("5,5" +System.lineSeparator() + "10")) {
                return Stream.of("5","5" + System.lineSeparator() + "10").
                    collect(Collectors.toList());
            }
            else {
                return Stream.of(arg).collect(Collectors.toList());
            }
        });

        when(mockLineStringSplitter.split(anyString())).thenAnswer(invocation -> {

            String arg = invocation.getArgument(0);
            if (arg != null && arg.equals("1" + System.lineSeparator() + "5")) {
                return Stream.of("1","5").collect(Collectors.toList());
            }
            else if (arg != null && arg.equals("5" + System.lineSeparator() + "10")) {
                return Stream.of("5","10").collect(Collectors.toList());
            }
            else {
                return Stream.of(arg).collect(Collectors.toList());
            }
        });

        splitter = new StringSplitter(mockCommaStringSplitter, mockLineStringSplitter);
    }

    @Test
    public void should_ReturnOneNumber() {

        List<Integer> expectedList = Stream.of(10).collect(Collectors.toList());
        List<Integer> returnedList = splitter.split("10");

        assertEquals(expectedList, returnedList);
    }

    @Test
    public void should_SplitCommaSeparatedString() {

        List<Integer> expectedList = Stream.of(1,5).collect(Collectors.toList());
        List<Integer> returnedList = splitter.split("1,5");

        assertEquals(expectedList, returnedList);
    }

    @Test
    public void should_SplitLineSeparatedString() {

        List<Integer> expectedList = Stream.of(1,5).collect(Collectors.toList());
        List<Integer> returnedList = splitter.split("1" + System.lineSeparator() + "5");

        assertEquals(expectedList, returnedList);
    }

    @Test
    public void should_SplitLineByCommaAndNewLine() {

        List<Integer> expectedList = Stream.of(5,5,10).collect(Collectors.toList());
        List<Integer> returnedList = splitter.split("5,5" + System.lineSeparator() + "10");

        assertEquals(expectedList, returnedList);
    }

    @Test
    public void should_IgnoreNumbersOver1000() {

        List<Integer> expectedList = Stream.of(9,1).collect(Collectors.toList());
        List<Integer> returnedList = splitter.split("9,5000,1");

        assertEquals(expectedList, returnedList);
    }

    @Test(expected = NumberFormatException.class)
    public void should_GetExceptionForNegativeNumbers() {
        splitter.split("10,-1");
    }
}
