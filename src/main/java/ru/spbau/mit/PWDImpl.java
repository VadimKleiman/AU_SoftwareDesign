package ru.spbau.mit;

import com.sun.istack.internal.NotNull;

/**
 * Print name of current/working directory
 */
public class PWDImpl implements Command {
    @Override
    public String run(@NotNull PipeStream pipe, String[] args, @NotNull Environment env) throws CommandException {
        if (env.read("DIRPATH") == null) {
            throw new CommandException("pwd");
        }
        return env.read("DIRPATH");
    }
}
