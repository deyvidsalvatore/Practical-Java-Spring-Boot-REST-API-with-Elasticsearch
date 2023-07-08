package com.deyvidsalvatore.praticaljava.exception;

import java.io.Serial;

public class IllegalApiParamException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public IllegalApiParamException(String s) {
        super(s);
    }
}
