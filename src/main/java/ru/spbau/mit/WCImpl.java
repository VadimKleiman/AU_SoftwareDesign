package ru.spbau.mit;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Print newline, word, and byte counts for each FILE, and a total line if
 * more than one FILE is specified
 */
public class WCImpl implements Command {

    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env) throws CommandException {
        String[] param = null;
        String p = pipe.read();
        if (args.length == 0 && p != null) {
            param = new String[1];
            param[0] = p;
        } else if (args.length != 0) {
            param = args;
        }
        if (param == null) {
            StringBuilder out = new StringBuilder();
            Scanner scan = new Scanner(System.in);
            do {
                String text = scan.nextLine();
                out.append(text);
                out.append("\n");
            }while(scan.hasNext());
            String tmp = out.toString();
            out.setLength(0);
            out.append(tmp.split("\n").length);
            out.append(" ");
            String[] lines = tmp.replaceAll("[\\s]{2,}", " ").trim().split("\n");
            int countWords = 0;
            for (String i : lines) {
                if (i.length() > 0) {
                    countWords += i.split(" ").length;
                }
            }
            out.append(countWords);
            out.append(" ");
            out.append(tmp.getBytes().length + 1);
            out.append("\n");
            return out.toString();
        } else {
            StringBuilder out = new StringBuilder();
            for (String i : param) {
                File f = new File(i);
                if (f.isFile()) {
                    try {
                        String tmp = new Scanner(f).useDelimiter("\\Z").next();
                        out.append(tmp.split("\n").length);
                        out.append(" ");
                        String[] lines = tmp.replaceAll("[\\s]{2,}", " ").trim().split("\n");
                        int countWords = 0;
                        for (String j : lines) {
                            if (j.length() > 0) {
                                countWords += j.split(" ").length;
                            }
                        }
                        out.append(countWords);
                        out.append(" ");
                        out.append(tmp.getBytes().length);
                        out.append(" ");
                        out.append(i);
                        out.append("\n");

                    }catch (FileNotFoundException ex) {
                        throw new CommandException("wc");
                    }
                } else {
                    if (args.length == 0){
                        out.append(param[0].split("\n").length);
                        out.append(" ");
                        out.append(param[0].replaceAll("[\\s]{2,}", " ").trim().split(" ").length);
                        out.append(" ");
                        out.append(param[0].getBytes().length + 1);
                        out.append("\n");
                    } else {
                        throw new CommandException("wc");
                    }
                }
            }
            return out.toString();
        }
    }
}
