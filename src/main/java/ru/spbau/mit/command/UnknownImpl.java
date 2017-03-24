package ru.spbau.mit.command;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Class for unknown commands
 */
public class UnknownImpl implements Command {
    /**
     * Command name
     */
    private String cmdName;

    public UnknownImpl(String cmdName) {
        this.cmdName = cmdName;
    }

    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env) throws CommandException {
        try {
            String[] param = new String[1];
            param[0] = cmdName;
            String p = pipe.read();
            if (args.length == 0 && p != null) {
                param = new String[2];
                param[0] = cmdName;
                param[1] = p;
            } else if (args.length != 0){
                param = new String[args.length + 1];
                param[0] = cmdName;
                System.arraycopy(args, 0, param, 1, args.length);
            }
            Process process = new ProcessBuilder(param).start();
            InputStream is = process.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line;
            StringBuilder out = new StringBuilder();
            while ((line = br.readLine()) != null) {
                out.append(line);
                out.append("\n");
            }
            return out.toString();
        } catch (Exception ex) {
            throw new CommandException(cmdName);
        }
    }
}
