package com.shopspark.ShopSpark.exceptions;

public class InvalidInputFormat extends Exception{
    public InvalidInputFormat() {
        super();
    }

    public InvalidInputFormat(String message) {
        super(message);
    }

    public InvalidInputFormat(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInputFormat(Throwable cause) {
        super(cause);
    }
}
