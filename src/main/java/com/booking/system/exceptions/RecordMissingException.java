package com.booking.system.exceptions;

public class RecordMissingException extends RuntimeException {
    public RecordMissingException(String message) {
        super(message);
    }
}
