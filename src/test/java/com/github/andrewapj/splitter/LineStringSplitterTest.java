package com.github.andrewapj.splitter;

import static org.junit.Assert.assertEquals;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;

public class LineStringSplitterTest {

    private final LineStringSplitter lineStringSplitter = new LineStringSplitter();

    @Test
    public void should_SplitLineSeparatedString() {

        assertEquals(Stream.of("1", "5").collect(Collectors.toList()),
            lineStringSplitter.split("1" + System.lineSeparator() + "5"));

        assertEquals(Stream.of("5", "10").collect(Collectors.toList()),
            lineStringSplitter.split("5" + System.lineSeparator() + "10"));
    }
}
