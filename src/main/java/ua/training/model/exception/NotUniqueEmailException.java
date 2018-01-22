package ua.training.model.exception;

public class NotUniqueEmailException extends RuntimeException {
    public NotUniqueEmailException(String message) {
        super(message);
    }
}
