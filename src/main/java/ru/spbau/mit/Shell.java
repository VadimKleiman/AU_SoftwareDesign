package ru.spbau.mit;

import java.util.List;
import java.util.Scanner;


/**
 * Command line interface
 */
public class Shell {
    /**
     * Run CLI
     */
    public static void run() {
        Environment env = new Environment();
        Parser parser = new Parser();
        env.write("DIRPATH", System.getProperty("user.dir"));
        Scanner scan = new Scanner(System.in);
            do {
                try {
                    String strInput = Preprocessor.replace(scan.nextLine(), env);
                    List<String> tokens = Tokenizer.getTokens(strInput);
                    List<Proc> process = parser.parse(tokens, env);
                    String out = null;
                    for (Proc i : process) {
                        out = i.exec();
                    }
                    if (out != null) {
                        System.out.println(out);
                    }
                } catch (PreprocessorException ex) {
                    System.out.println(ex.toString());
                } catch (ParserException ex) {
                    System.out.println(ex.toString());
                } catch (CommandException ex) {
                    if (ex.getCommand().equals("exit")) {
                        break;
                    } else {
                        System.out.println(ex.toString());
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            } while (scan.hasNext());

    }
    public static void main(String[] args) {
        run();
    }
}
