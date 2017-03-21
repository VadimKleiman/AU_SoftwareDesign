package ru.spbau.mit;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ParserTest {
    @Test
    public void parse() throws ParserException {
        Parser p = new Parser();
        Environment env = new Environment();
        List<String> test = new ArrayList<>();
        test.add("pwd");
        test.add("--all");
        test.add("|");
        test.add("echo");
        List<CommandRunner> commands = p.parse(test, env);
        assertTrue(commands.get(0).getCommand() instanceof PWDImpl);
        assertTrue(commands.get(1).getCommand() instanceof EchoImpl);
    }
}