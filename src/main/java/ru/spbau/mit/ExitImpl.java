package ru.spbau.mit;

import com.sun.istack.internal.NotNull;

/**
 * Cause normal process termination
 */
public class ExitImpl implements Command {

    @Override
    public String run(@NotNull PipeStream pipe, String[] args, @NotNull Environment env) throws CommandException {
        throw new CommandException("exit");
    }
}
