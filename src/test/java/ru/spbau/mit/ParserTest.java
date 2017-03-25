package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.command.*;
import ru.spbau.mit.kernel.CommandRunner;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.Parser;
import ru.spbau.mit.kernel.exceptions.ParserException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ParserTest {
    @Test
    public void ParserSimpleTest() throws ParserException {
        Parser p = new Parser();
        Environment env = new Environment();
        List<String> test = new ArrayList<>();
        test.add("pwd");
        test.add("--all");
        test.add("|");
        test.add("echo");
        test.add("|");
        test.add("cat");
        test.add("|");
        test.add("ls");
        test.add("|");
        test.add("wc");
        List<CommandRunner> commands = p.parse(test, env);
        assertTrue(commands.get(0).getCommand() instanceof PWDImpl);
        assertTrue(commands.get(1).getCommand() instanceof EchoImpl);
        assertTrue(commands.get(2).getCommand() instanceof CatImpl);
        assertTrue(commands.get(3).getCommand() instanceof UnknownImpl);
        assertTrue(commands.get(4).getCommand() instanceof WCImpl);
    }

    @Test(expected = ParserException.class)
    public void ParserThrowTest() throws ParserException {
        Parser p = new Parser();
        Environment env = new Environment();
        List<String> test = new ArrayList<>();
        test.add("|");
        p.parse(test, env);
    }
}