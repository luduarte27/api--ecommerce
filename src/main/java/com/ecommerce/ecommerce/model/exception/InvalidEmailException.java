package com.ecommerce.ecommerce.model.exception;

public class InvalidEmailException extends RuntimeException{

    public InvalidEmailException(String mensagem){
        super(mensagem);
    }

}
