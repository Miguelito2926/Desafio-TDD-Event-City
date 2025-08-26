package com.devsuperior.bds02.services.exceptions;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String mgs) {
        super(mgs);
    }
}