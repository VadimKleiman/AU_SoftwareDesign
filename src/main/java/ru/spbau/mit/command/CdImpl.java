package ru.spbau.mit.command;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.command.exceptions.CommandException;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Command used to change the current working directory.
 * It set current directory to its first argument, if it's provided, or to user home directory.
 */
public class CdImpl implements Command {
    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env)
            throws CommandException {
        final Path path = (args.length == 0) ?
            Paths.get(System.getProperty("user.home")) :
            Paths.get(env.read("DIRPATH")).resolve(args[0]);

        final String pathName = path.toAbsolutePath().normalize().toString();
        if (!new File(pathName).exists()) {
            throw new CommandException("cd: path does not exists (" + pathName + ")");
        }

        env.write("DIRPATH", pathName);
        return null;
    }
}
