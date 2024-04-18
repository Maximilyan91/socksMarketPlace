package com.sockMarket.exception;

public class ValidationException extends RuntimeException{

    public ValidationException(String message) {
        super("Ошибка валидации сущности " + message);
    }
}
