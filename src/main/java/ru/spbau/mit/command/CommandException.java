package ru.spbau.mit.command;

import org.jetbrains.annotations.NotNull;

/**
 * Class that represents exception that may
 * arise during command execution
 */
public class CommandException extends Exception {
    private final String cmdName;

    CommandException(@NotNull String element) {
        cmdName = element;
    }

    @Override
    public String toString() {
        return "Command name: " + cmdName;
    }

    public String getCommand() {
        return cmdName;
    }
}
