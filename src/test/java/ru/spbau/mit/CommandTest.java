package ru.spbau.mit;

import org.junit.Test;
import ru.spbau.mit.command.*;
import ru.spbau.mit.command.exceptions.CommandException;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class CommandTest {
    private Environment env = new Environment();

    @Test
    public void CommandWCReadSTDInputTest() throws CommandException, IOException {
        PipeStream pipe = new PipeStream();
        Command wc = new WCImpl();
        pipe.write("Hello World!\nJAVA");
        String out = wc.run(pipe, new String[0], env);
        assertEquals("2 3 16\n", out);
    }

    @Test
    public void CommandWCReadFileTest() throws CommandException, IOException {
        PipeStream pipe = new PipeStream();
        Command wc = new WCImpl();
        String[] args = {"src/test/resources/test.txt"};
        String out = wc.run(pipe, args, env);
        assertEquals("2 3 16\n", out);
    }

    @Test
    public void CommandUnknownTest() throws CommandException {
        PipeStream pipe = new PipeStream();
        UnknownImpl ls = new UnknownImpl("ls");
        String[] args = {"-a", "src/test/resources/"};
        String out = ls.run(pipe, args, env);
        assertEquals(".\n..\ntest.txt\n", out);
    }

    @Test
    public void CommandCatTest() throws CommandException, IOException {
        Command cat = new CatImpl();
        PipeStream pipe = new PipeStream();
        String[] args = {"src/test/resources/test.txt"};
        String out = cat.run(pipe, args, env);
        assertEquals("Hello World!\nJAVA", out);
    }

    @Test
    public void CommandEchoTest() throws CommandException, UnsupportedEncodingException {
        PipeStream pipe = new PipeStream();
        String[] args = {"Hello", "World", "!"};
        Command echo = new EchoImpl();
        String out = echo.run(pipe, args, env);
        assertEquals("Hello World !", out);
    }

    @Test
    public void CommandPWDTest() throws CommandException, UnsupportedEncodingException {
        PipeStream pipe = new PipeStream();
        env.write("DIRPATH", System.getProperty("user.dir"));
        String pwd = System.getProperty("user.dir");
        Command cmd = new PWDImpl();
        String out = cmd.run(pipe, new String[0], env);
        assertEquals(pwd, out);
    }

    @Test(expected = CommandException.class)
    public void CommandExitTest() throws CommandException, UnsupportedEncodingException {
        PipeStream pipe = new PipeStream();
        Command exit = new ExitImpl();
        exit.run(pipe, new String[0], env);
    }
    @Test
    public void CommandGrepTest() throws CommandException, UnsupportedEncodingException  {
        PipeStream pipe = new PipeStream();
        Command grep = new GrepImpl();
        String[] args = {"Hello"};
        pipe.write("Hello World!");
        String out = grep.run(pipe, args, env);
        String check = "Hello World!\n";
        assertEquals(out, check);
    }
    @Test
    public void CommandGrepIgnoreCaseTest() throws CommandException, UnsupportedEncodingException {
        PipeStream pipe = new PipeStream();
        Command grep = new GrepImpl();
        String[] args = {"hello", "-i"};
        pipe.write("Hello World!");
        String out = grep.run(pipe, args, env);
        String check = "Hello World!\n";
        assertEquals(out, check);
        String[] args2 = {"hello"};
        pipe.write("Hello World!");
        out = grep.run(pipe, args2, env);
        check = "";
        assertEquals(out, check);
    }
    @Test
    public void CommandGrepWholeWordTest() throws CommandException, UnsupportedEncodingException {
        PipeStream pipe = new PipeStream();
        Command grep = new GrepImpl();
        String[] args = {"hello", "-w", "-i"};
        pipe.write("Hello World!");
        String out = grep.run(pipe, args, env);
        String check = "Hello World!\n";
        assertEquals(out, check);
        String[] args2 = {"hel", "-w", "-i"};
        pipe.write("Hello World!");
        out = grep.run(pipe, args2, env);
        check = "";
        assertEquals(out, check);
    }
    @Test
    public void CommandGrepNLinesTest() throws CommandException, UnsupportedEncodingException {
        PipeStream pipe = new PipeStream();
        Command grep = new GrepImpl();
        String[] args = {"hello", "-w", "-i", "-A", "2"};
        pipe.write("123\nHello World!\n1\n2\n3\n4");
        String out = grep.run(pipe, args, env);
        String check = "Hello World!\n1\n2\n";
        assertEquals(out, check);
    }
}
