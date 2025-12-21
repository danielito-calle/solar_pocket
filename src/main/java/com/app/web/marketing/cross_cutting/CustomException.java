package com.app.web.marketing.cross_cutting;

public abstract class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}

