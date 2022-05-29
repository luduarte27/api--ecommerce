package com.ecommerce.ecommerce.model;

public enum Status {
    FINALIZADO("Finalizado"),
    NAO_FINALIZADO("Não Finalizado");

    private final String valor;

    private Status(String valor){
        this.valor = valor;
    }

    public String getValor(){
        return this.valor;
    }
}
