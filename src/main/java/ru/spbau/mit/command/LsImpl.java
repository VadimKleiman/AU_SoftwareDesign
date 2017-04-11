package ru.spbau.mit.command;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.command.exceptions.CommandException;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.File;
import java.util.Arrays;

/**
 * Command to list files.
 * It prints files from directory, specified by its arguments, if it's provided, or from current directory.
 */
public class LsImpl implements Command {
    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env)
            throws CommandException {
        final StringBuilder out = new StringBuilder();
        final String path = (args.length > 0) ? args[0] : env.read("DIRPATH");
        final File directory = new File(path);

        if (!directory.exists()) {
            throw new CommandException("ls");
        }
        final File[] files = new File(path).listFiles();
        Arrays.sort(files);

        for (File f : files) {
            out.append(f.getName());
            out.append('\n');
        }
        return out.toString();
    }
}

