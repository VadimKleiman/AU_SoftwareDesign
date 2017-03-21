package ru.spbau.mit;

import org.jetbrains.annotations.NotNull;

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
