package ru.spbau.mit;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ShellTest {
    @Test
    public void cmd() {
        try {
            Environment env = new Environment();
            Parser parser = new Parser();
            env.write("DIRPATH", System.getProperty("user.dir"));
            String test = "echo \"Wello World!\" | wc";
            String strInput = Preprocessor.replace(test, env);
            List<String> tokens = Tokenizer.getTokens(strInput);
            List<Proc> process = parser.parse(tokens, env);
            String out = null;
            for (Proc i : process) {
                out = i.exec();
            }
            assertEquals("1 2 13\n", out);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}