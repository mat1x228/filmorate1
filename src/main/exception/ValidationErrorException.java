package ru.yandex.practicum.filmorate.src.main.exception;

public class ValidationErrorException extends RuntimeException {
    public ValidationErrorException(String message) {
        super(message);
    }
}
