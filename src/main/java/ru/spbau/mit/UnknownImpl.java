package ru.spbau.mit;

import com.sun.istack.internal.NotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;

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
    public String run(@NotNull PipeStream pipe, String[] args, @NotNull Environment env) throws CommandException {
        try {
            String[] param = new String[1];
            param[0] = cmdName;
            String p = pipe.read();
            if (args == null && p != null) {
                param = new String[2];
                param[0] = cmdName;
                param[1] = p;
            } else if (args != null){
                param = new String[args.length + 1];
                param[0] = cmdName;
                for (int i = 0; i < args.length; i++) {
                    param[i + 1] = args[i];
                }
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
