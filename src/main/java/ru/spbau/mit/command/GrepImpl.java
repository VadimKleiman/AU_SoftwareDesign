package ru.spbau.mit.command;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.command.exceptions.CommandException;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GrepImpl implements Command {
    private class Args {
        @Parameter
        private ArrayList<String> parameters = new ArrayList<>();

        @Parameter(names = {"-i"},
                description = "Ignore case distinctions")
        private boolean i = false;

        @Parameter(names = {"-w"},
                description = "Select lines that contain only the whole word")
        private boolean w = false;
        @Parameter(names = {"-A"}, description = "Print N lines of trailing context after matching")
        private int n = 0;
    }

    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env)
            throws CommandException, UnsupportedEncodingException {
        Args params = new Args();
        JCommander cmd = new JCommander(params);
        cmd.parse(args);
        Pattern pattern;
        String p = pipe.read();
        if (p == null && params.parameters.size() < 2) {
            throw new CommandException("Grep");
        }
        String patternString = params.parameters.get(0);
        String fileName = null;
        BufferedReader br;
        StringBuilder out = new StringBuilder();
        String currentLine;
        int current = -1;
        if (params.parameters.size() > 1) {
            fileName = params.parameters.get(1);
        }
        if (params.w) {
            patternString = "\\b" + patternString + "\\b";
        }
        if (params.i) {
            pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        } else {
            pattern = Pattern.compile(patternString);
        }
        if (fileName != null) {
            try {
                br = new BufferedReader(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                throw  new CommandException("Grep");
            }
        } else {
            assert p != null;
            InputStream input = new ByteArrayInputStream(p.getBytes(StandardCharsets.UTF_8));
            br = new BufferedReader(new InputStreamReader(input));

        }
        try{
            while ((currentLine = br.readLine()) != null) {
                Matcher m = pattern.matcher(currentLine);
                if (m.find()) {
                    current = params.n;
                }
                if (current >= 0) {
                    out.append(currentLine).append("\n");
                    current--;
                }
            }
        } catch (IOException e) {
            throw new CommandException("Grep");
        }
        return out.toString();
    }
}
