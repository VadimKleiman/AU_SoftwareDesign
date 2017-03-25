package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.Preprocessor;
import ru.spbau.mit.kernel.PreprocessorException;

import static org.junit.Assert.*;

public class PreprocessorTest {
    private Environment env = new Environment();

    @Test
    public void PreprocessorReplaceTest() throws PreprocessorException {
        String test = "echo $x \"$x\" \'$x\' \"$xx\"";
        env.write("$x", "Hello");
        assertEquals("echo Hello \"Hello\" \'$x\' \"$xx\"", Preprocessor.replace(test, env));
    }

    @Test(expected = PreprocessorException.class)
    public void PreprocessorExceptionTest() throws PreprocessorException {
        Preprocessor.replace("test's", env);
    }

}