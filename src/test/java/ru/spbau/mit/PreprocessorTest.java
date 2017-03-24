package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.Preprocessor;
import ru.spbau.mit.kernel.PreprocessorException;

import static org.junit.Assert.*;

public class PreprocessorTest {
    @Test
    public void replace() throws PreprocessorException {
        String test = "echo $x \"$x\" \'$x\' \"$xx\"";
        Environment env = new Environment();
        env.write("$x", "Hello");
        assertEquals("echo Hello \"Hello\" \'$x\' \"$xx\"", Preprocessor.replace(test, env));
    }
}