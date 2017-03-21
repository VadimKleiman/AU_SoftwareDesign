package ru.spbau.mit;

import org.jetbrains.annotations.NotNull;

public final class Preprocessor {
    /**
     * Takes raw string from stdin and preprocess. It substitutes every occurrence of variable with '$' on it's value from
     * the environment. Variable in single quotes are skipped.
     * @param element row input string
     * @param env environments
     * @return preprocess string
     * @throws PreprocessorException
     */
    public static String replace(@NotNull String element, @NotNull Environment env) throws PreprocessorException {
        StringBuilder out = new StringBuilder();
        int begin = 0;
        if ((element.length() - element.replace("\"", "").length()) % 2 != 0
                || (element.length() - element.replace("\'", "").length()) % 2 != 0) {
            throw new PreprocessorException();
        }
        for (int i = 0; i < element.length(); i++) {
            if (element.charAt(i) == '\'') {
                out.append(element.charAt(i++));
                while (element.charAt(i) != '\'') {
                    out.append(element.charAt(i));
                    i++;
                }
            }
            if (i < element.length() && element.charAt(i) == '$') {
                begin = i;
                while (i < element.length() && element.charAt(i) != ' ' && element.charAt(i) != '\"'
                        && element.charAt(i) != '\'') {
                    i++;
                }
                String subElement = element.substring(begin, i);
                String value = env.read(subElement);
                if (value != null) {
                    out.append(value);
                } else {
                    out.append(subElement);
                }
            }
            if (i < element.length()) {
                out.append(element.charAt(i));
            }
        }
        return out.toString();
    }
}
