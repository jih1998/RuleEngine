package com.example.easyruledemo.exception;

public class BusinessException extends RuntimeException{
    public BusinessException(String msg){
        super(msg);
    }

    public BusinessException(){

    }
}
