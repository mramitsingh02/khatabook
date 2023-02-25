package com.generic.khatabook.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(final String id) {
        super("khatabook " + id + " not fount!");
    }
}
