package ru.spbau.mit.command;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.UnsupportedEncodingException;

/**
 * Public interface for commands
 */

public interface Command {
    /**
     * Method runs the command
     * @param pipe input/output stream
     * @param args arguments
     * @param env environment variables
     * @return the result of running the command
     * @throws CommandException if there where errors during command execution
     */
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env) throws CommandException, UnsupportedEncodingException;
}
