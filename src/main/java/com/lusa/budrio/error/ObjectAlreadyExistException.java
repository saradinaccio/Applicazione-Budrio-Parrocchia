package com.lusa.budrio.error;

public final class ObjectAlreadyExistException extends RuntimeException {

    public ObjectAlreadyExistException() {
        super();
    }

    public ObjectAlreadyExistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public ObjectAlreadyExistException(final String message) {
        super(message);
    }

    public ObjectAlreadyExistException(final Throwable cause) {
        super(cause);
    }
}
