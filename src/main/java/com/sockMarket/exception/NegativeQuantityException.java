package com.sockMarket.exception;

public class NegativeQuantityException extends RuntimeException{

    public NegativeQuantityException(String message) {
        super(message);
    }
}
