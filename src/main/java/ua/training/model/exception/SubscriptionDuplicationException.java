package ua.training.model.exception;

public class SubscriptionDuplicationException extends RuntimeException {
    public SubscriptionDuplicationException(String message) {
        super(message);
    }
}
