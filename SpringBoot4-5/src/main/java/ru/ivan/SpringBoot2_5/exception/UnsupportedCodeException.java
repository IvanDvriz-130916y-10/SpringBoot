package ru.ivan.SpringBoot2_5.exception;
public class UnsupportedCodeException extends RuntimeException {
    public UnsupportedCodeException(String message) {
        super(message);
    }
}