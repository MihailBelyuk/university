package com.solvd.university.domain.exception;

public class NotFullInformationProvidedException extends RuntimeException {

    public NotFullInformationProvidedException() {
        super();
    }

    public NotFullInformationProvidedException(String message) {
        super(message);
    }

    public NotFullInformationProvidedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFullInformationProvidedException(Throwable cause) {
        super(cause);
    }
}
