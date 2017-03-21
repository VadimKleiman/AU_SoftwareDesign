package ru.spbau.mit;

import org.jetbrains.annotations.NotNull;

/**
 * This class collect command context
 */
public class CommandRunner {
    private Environment env;
    private Command cmd;
    private String[] args;
    private PipeStream pipe;

    public CommandRunner(@NotNull PipeStream pipe, @NotNull Command cmd, String[] args, @NotNull Environment env) {
        this.pipe = pipe;
        this.cmd = cmd;
        this.args = args;
        this.env = env;
    }

    /**
     * Execute command
     * @return result of execute command
     * @throws CommandException
     */
    public String exec() throws CommandException {
        String result = cmd.run(pipe, args, env);
        pipe.write(result);
        return result;
    }

    /**
     * Returns a reference to an object of type Command
     */
    public Command getCommand() {
        return cmd;
    }
}
