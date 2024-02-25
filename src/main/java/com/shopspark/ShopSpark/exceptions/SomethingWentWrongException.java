package com.shopspark.ShopSpark.exceptions;

public class SomethingWentWrongException extends Exception{
    public SomethingWentWrongException() {
        super();
    }

    public SomethingWentWrongException(String message) {
        super(message);
    }

    public SomethingWentWrongException(String message, Throwable cause) {
        super(message, cause);
    }

    public SomethingWentWrongException(Throwable cause) {
        super(cause);
    }
}
