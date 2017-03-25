package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.command.CommandException;
import ru.spbau.mit.kernel.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.Assert.*;

public class ShellTest {
    @Test
    public void ShellSimpleTest() throws PreprocessorException, ParserException, CommandException, UnsupportedEncodingException {
        Environment env = new Environment();
        Parser parser = new Parser();
        env.write("DIRPATH", System.getProperty("user.dir"));
        String test = "echo \"Wello World!\" | wc";
        String strInput = Preprocessor.replace(test, env);
        List<String> tokens = Tokenizer.getTokens(strInput);
        List<CommandRunner> process = parser.parse(tokens, env);
        String out = null;
        for (CommandRunner i : process) {
            out = i.exec();
        }
        assertEquals("1 2 12\n", out);
    }
}