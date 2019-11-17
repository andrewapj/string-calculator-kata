package com.github.andrewapj;

import com.github.andrewapj.splitter.CommaStringSplitter;
import com.github.andrewapj.splitter.LineStringSplitter;
import com.github.andrewapj.splitter.StringSplitter;

public class App
{
    public static void main(String[] args )
    {
        StringSplitter splitter = new StringSplitter(new CommaStringSplitter(),new LineStringSplitter());
        System.out.println("The total is: " + new StringCalculator(splitter).calculate(args[0]));
    }
}
