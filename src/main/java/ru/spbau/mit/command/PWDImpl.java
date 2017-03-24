package ru.spbau.mit.command;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

/**
 * Print name of current/working directory
 */
public class PWDImpl implements Command {
    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env) throws CommandException {
        if (env.read("DIRPATH") == null) {
            throw new CommandException("pwd");
        }
        return env.read("DIRPATH");
    }
}
