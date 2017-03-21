package ru.spbau.mit;

import org.jetbrains.annotations.NotNull;

/**
 * Cause normal process termination
 */
public class ExitImpl implements Command {

    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env) throws CommandException {
        throw new CommandException("exit");
    }
}
