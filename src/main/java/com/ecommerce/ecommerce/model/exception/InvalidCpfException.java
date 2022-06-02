package com.ecommerce.ecommerce.model.exception;

public class InvalidCpfException extends RuntimeException{
    
    public InvalidCpfException(String mensagem){
        super(mensagem);
    }
}
