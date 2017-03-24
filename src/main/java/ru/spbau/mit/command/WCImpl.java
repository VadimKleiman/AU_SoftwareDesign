package ru.spbau.mit.command;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.*;
import java.util.Scanner;

/**
 * Print newline, word, and byte counts for each FILE, and a total line if
 * more than one FILE is specified
 */
public class WCImpl implements Command {

    private int countWords(String text) {
        int words = 0;
        for (String i : text.split("\\s+")) {
            if (i.length() != 0) {
                words++;
            }
        }
        return words;
    }

    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env)
            throws CommandException, UnsupportedEncodingException {
        String[] param = null;
        int bytes = 0;
        int words = 0;
        int lines = 0;
        String p = pipe.read();
        if (p != null) {
            for (String i : p.split("\n")) {
                bytes += i.getBytes("UTF-8").length;
                words += countWords(i);
                lines++;
            }
            return Integer.toString(lines) +
                    " " + Integer.toString(words) +
                    " " + Integer.toString(bytes) +
                    "\n";
        } else if (args.length != 0) {
            param = args;
        }
        if (param == null) {
            Scanner scan = new Scanner(System.in);
            do {
                String text = scan.nextLine();
                bytes += text.getBytes("UTF-8").length;
                words += countWords(text);
                lines++;
            } while(scan.hasNext());
        } else {
            for (String name : param) {
                try (BufferedReader br = new BufferedReader(new FileReader(name))) {
                    String text;
                    while ((text = br.readLine()) != null) {
                        bytes += text.getBytes("UTF-8").length;
                        words += countWords(text);
                        lines++;
                    }
                } catch (IOException ex) {
                    throw new CommandException("wc");
                }
            }
        }
        return Integer.toString(lines) +
                " " + Integer.toString(words) +
                " " + Integer.toString(bytes) +
                "\n";
    }
}
