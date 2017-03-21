package ru.spbau.mit;

import org.jetbrains.annotations.NotNull;

/**
 * Public interface for commands
 */

public interface Command {
    /**
     * Method runs the command
     * @param pipe input/output stream
     * @param args arguments
     * @param env environments
     * @return the result of running the command
     * @throws CommandException
     */
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env) throws CommandException;
}
