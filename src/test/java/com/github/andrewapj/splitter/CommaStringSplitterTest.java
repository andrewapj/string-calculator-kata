package com.github.andrewapj.splitter;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class CommaStringSplitterTest {

    private final CommaStringSplitter commaStringSplitter = new CommaStringSplitter();

    @Test
    public void should_SplitCommaStrings() {

        assertEquals(Stream.of("1", "5").collect(Collectors.toList()),
            commaStringSplitter.split("1,5"));

        assertEquals(Stream.of("9", "5000", "1").collect(Collectors.toList()),
            commaStringSplitter.split("9,5000,1"));

        assertEquals(Stream.of("10", "-1").collect(Collectors.toList()),
            commaStringSplitter.split("10,-1"));

        assertEquals(Stream.of("5", "5" + System.lineSeparator() + "10").collect(Collectors.toList()),
            commaStringSplitter.split("5,5" + System.lineSeparator() + "10"));
    }
}
