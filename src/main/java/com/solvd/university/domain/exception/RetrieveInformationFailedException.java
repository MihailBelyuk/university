package com.solvd.university.domain.exception;

public class RetrieveInformationFailedException extends RuntimeException {

    public RetrieveInformationFailedException() {
        super();
    }

    public RetrieveInformationFailedException(String message) {
        super(message);
    }

    public RetrieveInformationFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public RetrieveInformationFailedException(Throwable cause) {
        super(cause);
    }
}
