package com.app.web.marketing.cross_cutting;

public class RepeatedSubmitterException extends CustomException {

    private static final String DEFAULT_MESSAGE = "Ya has enviado anteriormente.";

    public RepeatedSubmitterException() {
        super(DEFAULT_MESSAGE);
    }

    public RepeatedSubmitterException(String detail) {
        super(DEFAULT_MESSAGE + " Detalle: " + detail);
    }
}