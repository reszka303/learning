package javaStart.task39_Streams.exercise2.combined.exception;

public class NumberPositiveException extends RuntimeException {
    public NumberPositiveException(String message) {
        System.err.println(message);
    }
}
