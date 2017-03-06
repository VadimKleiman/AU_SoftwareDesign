package ru.spbau.mit;

import com.sun.istack.internal.NotNull;

/**
 * Runtime error command
 */
public class CommandException extends Exception {
    private String cmdName;
    CommandException(@NotNull String element) {
        cmdName = element;
    }

    public String toString() {
        return "Command name: " + cmdName;
    }

    public String getCommand() {
        return cmdName;
    }
}
