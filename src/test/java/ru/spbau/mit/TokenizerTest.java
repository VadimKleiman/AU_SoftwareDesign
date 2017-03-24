package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.kernel.Tokenizer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TokenizerTest {
    @Test
    public void oneCommand() {
        String test = "echo \"Hello World!\"";
        List<String> out = new ArrayList<>();
        out.add("echo");
        out.add("\"Hello World!\"");
        assertEquals(out, Tokenizer.getTokens(test));
    }

    @Test
    public void pipe() {
        String test = "echo \"Hello World!\" | pwd";
        List<String> out = new ArrayList<>();
        out.add("echo");
        out.add("\"Hello World!\"");
        out.add("|");
        out.add("pwd");
        assertEquals(out, Tokenizer.getTokens(test));
    }

    @Test
    public void args() {
        String test = "echo -l --all \"Hello World! Test=Test\"";
        List<String> out = new ArrayList<>();
        out.add("echo");
        out.add("-l");
        out.add("--all");
        out.add("\"Hello World! Test=Test\"");
        assertEquals(out, Tokenizer.getTokens(test));
    }

    @Test
    public void equalsOperator() {
        String test = "x=Hello";
        List<String> out = new ArrayList<>();
        out.add("x");
        out.add("=");
        out.add("Hello");
        assertEquals(out, Tokenizer.getTokens(test));
    }
}