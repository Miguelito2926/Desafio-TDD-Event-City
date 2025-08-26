package com.devsuperior.bds02.services.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String mgs) {
        super(mgs);
    }
}