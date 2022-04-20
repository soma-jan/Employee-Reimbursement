package com.revature.exception;

public class FailedLoginException extends Exception {
    public  FailedLoginException(String message){
        super(message);
    }
}
