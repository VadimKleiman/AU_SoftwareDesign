package ru.spbau.mit;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for syntax analysis
 */
public class Parser {
    /**
     *
     */
    private class Factory {
        public Command getCommand(String element) {
            switch (element) {
                case "pwd":
                    return new PWDImpl();
                case "echo":
                    return new EchoImpl();
                case "cat":
                    return new CatImpl();
                case "wc":
                    return new WCImpl();
                case "exit":
                    return new ExitImpl();
                default:
                    return new UnknownImpl(element);
            }
        }
    }

    /**
     *
     * @param tokens
     * @return
     */
    private List<List<String>> split(@NotNull List<String> tokens) {
        List<List<String>> splitTokens = new ArrayList<>();
        int begin = 0;
        for (int i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("|")) {
                splitTokens.add(new ArrayList<>(tokens.subList(begin, i)));
                begin = i + 1;
            }
        }
        splitTokens.add(new ArrayList<>(tokens.subList(begin, tokens.size())));
        return splitTokens;
    }

    /**
     *
     * @param tokens
     * @param env
     * @return
     * @throws ParserException
     */
    public List<Proc> parse(@NotNull List<String> tokens, @NotNull Environment env) throws ParserException {
        for(String i : tokens) {
            if (i.length() == 0) {
                throw new ParserException();
            }
        }
        Factory f = new Factory();
        List<Proc> out = new ArrayList<>();
        List<List<String>> splitTokens = split(tokens);
        PipeStream pipe = new PipeStream();
        for (List<String> i : splitTokens) {
            if (i.size() == 3 && i.get(1).equals("=")) {
                env.write("$" + i.get(0), i.get(2));
                continue;
            }
            if (i.size() > 1) {
                String[] args = new String[i.size() - 1];
                for (int j = 1; j < i.size(); j++) {
                    args[j - 1] = i.get(j);
                }
                out.add(new Proc(pipe, f.getCommand(i.get(0)), args, env));
            } else {
                out.add(new Proc(pipe, f.getCommand(i.get(0)), null, env));
            }
        }
        return out;
    }
}
