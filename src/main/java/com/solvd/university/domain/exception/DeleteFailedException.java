package com.solvd.university.domain.exception;

public class DeleteFailedException extends RuntimeException {

    public DeleteFailedException() {
        super();
    }

    public DeleteFailedException(String message) {
        super(message);
    }

    public DeleteFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteFailedException(Throwable cause) {
        super(cause);
    }
}
