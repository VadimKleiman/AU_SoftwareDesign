package ru.spbau.mit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.spbau.mit.command.Command;
import ru.spbau.mit.command.LsImpl;
import ru.spbau.mit.command.exceptions.CommandException;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertArrayEquals;

public class LsTest {
    private Environment env;
    private File file1;
    private File file2;
    private File subfolder;
    private File file3;
    @Rule
    public TemporaryFolder folder= new TemporaryFolder();

    @Before
    public void init() throws IOException {
        env = new Environment();
        file1 = folder.newFile("file1.txt");
        file2 = folder.newFile("file2.txt");
        subfolder = folder.newFolder("subfolder");
        file3 = folder.newFile("subfolder/file3.txt");
    }

    @Test
    public void CommandLsDefault() throws IOException, CommandException, UnsupportedEncodingException {
        final String folderPath = folder.getRoot().getAbsolutePath().toString();
        final String[] expected = new String[] {file1.getName(), file2.getName(), subfolder.getName()};

        env.write("DIRPATH", System.getProperty("user.dir"));
        Command cmd = new LsImpl();

        final PipeStream pipe = new PipeStream();
        final String res = cmd.run(pipe, new String[]{folderPath}, env);
        assertArrayEquals(expected, res.split("\n"));
    }

    @Test
    public void CommandLsTest() throws IOException, CommandException, UnsupportedEncodingException {
        final String folderPath = folder.getRoot().getAbsolutePath().toString();
        final String[] expected = new String[] {file1.getName(), file2.getName(), subfolder.getName()};

        env.write("DIRPATH", System.getProperty("user.dir"));
        Command cmd = new LsImpl();

        final PipeStream pipe = new PipeStream();
        env.write("DIRPATH", folderPath);
        final String res = cmd.run(pipe, new String[]{folder.getRoot().getAbsolutePath().toString()}, env);
        assertArrayEquals(expected, res.split("\n"));
    }
}
