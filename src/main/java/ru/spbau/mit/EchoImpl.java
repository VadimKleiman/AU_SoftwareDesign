package ru.spbau.mit;

import org.jetbrains.annotations.NotNull;

/**
 * Display a line of text
 */
public class EchoImpl implements Command {
    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env) throws CommandException {
        StringBuilder out = new StringBuilder();
        if (args.length > 0) {
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
