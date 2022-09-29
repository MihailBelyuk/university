package com.solvd.university.domain.exception;

public class VehicleTypeNotSupportedException extends RuntimeException {

    public VehicleTypeNotSupportedException() {
        super();
    }

    public VehicleTypeNotSupportedException(String message) {
        super(message);
    }

    public VehicleTypeNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

    public VehicleTypeNotSupportedException(Throwable cause) {
        super(cause);
    }
}
