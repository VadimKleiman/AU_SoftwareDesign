package ru.spbau.mit;

import com.sun.istack.internal.NotNull;

/**
 * Display a line of text
 */
public class EchoImpl implements Command {
    @Override
    public String run(@NotNull PipeStream pipe, String[] args, @NotNull Environment env) throws CommandException {
        StringBuilder out = new StringBuilder();
        if (args != null) {
            for (String i : args) {
                out.append(i.replace("\"", ""));
                out.append(" ");
            }
        } else {
            out.append(pipe.read());
        }
        return out.toString().trim();
    }
}
