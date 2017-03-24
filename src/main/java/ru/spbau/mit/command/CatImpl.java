package ru.spbau.mit.command;

import org.jetbrains.annotations.NotNull;
import ru.spbau.mit.kernel.Environment;
import ru.spbau.mit.kernel.PipeStream;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Concatenate files and print on the standard output
 */
public class CatImpl implements Command {
    @Override
    public String run(@NotNull PipeStream pipe, @NotNull String[] args, @NotNull Environment env) throws CommandException {
        if (args.length > 0) {
            StringBuilder out = new StringBuilder();
            for (String i : args) {
                File f = new File(i);
                if (f.isFile()) {
                    try {
                        out.append(new Scanner(f).useDelimiter("\\Z").next());
                    } catch (FileNotFoundException ex) {
                        throw new CommandException("Cat");
                    }
                } else {
                    throw new CommandException("Cat");
                }
            }
            return out.toString();
        } else {
            StringBuilder out = new StringBuilder();
            Scanner scan = new Scanner(System.in);
            do {
                String text = scan.nextLine();
                System.out.println(text);
                out.append(text);
            } while(scan.hasNext());
            return out.toString();
        }
    }
}
