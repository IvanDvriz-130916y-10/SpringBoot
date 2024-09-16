package ru.dvornikov.SpringBoot2.exception;

public class ValidationFailedException extends Exception {
    public ValidationFailedException(String message) {
        super(message);
    }

    public static class UnsupportedCodeException extends ValidationFailedException {
        public UnsupportedCodeException(String message) {
            super(message);
        }
    }
}