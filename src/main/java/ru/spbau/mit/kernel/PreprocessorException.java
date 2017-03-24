package ru.spbau.mit.kernel;

/**
 * Error preprocessing
 */
public class PreprocessorException extends Exception {
    @Override
    public String toString() {
        return "Preprocessor error!";
    }
}
