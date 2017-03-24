package ru.spbau.mit.kernel;

import org.jetbrains.annotations.NotNull;

/**
 * Input/Output stream
 */
public class PipeStream {
    private String buffer;

    /**
     * Reads from the stream
     */
    public String read() {
        String tmp = buffer;
        buffer = null;
        return tmp;
    }

    /**
     * Write to the stream
     */
    public void write(@NotNull String element) {
        buffer = element;
    }
}
