package ru.spbau.mit.kernel;

/**
 * Class that represents exception that may
 * arise during processing raw input string
 */
public class PreprocessorException extends Exception {
    @Override
    public String toString() {
        return "Preprocessor error!";
    }
}
