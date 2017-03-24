package ru.spbau.mit.kernel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Tokenizer {
    /**
     * Method splits the string into tokens
     * @param element string for separation into tokens
     * @return list of tokens
     */
    public static List<String> getTokens(@NotNull String element) {
        List<String> tokens = new ArrayList<>();
        element = element.replaceAll("=", " = ");
        Pattern regex = Pattern.compile("[^\\s\"']+|\"[^\"]*\"|'[^']*'");
        Matcher regexMatcher = regex.matcher(element);
        while (regexMatcher.find()) {
            tokens.add(regexMatcher.group().replaceAll(" = ", "="));
        }
        return tokens;
    }
}
