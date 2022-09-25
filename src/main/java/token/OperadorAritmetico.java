/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package token;

/**
 *
 * @author gusta
 */
public enum OperadorAritmetico {
    ADICAO("+"),
    SUBTRACAO("-"),
    MULTIPLICACAO("*"),
    DIVISAO("/");

    private final String valor;

    private OperadorAritmetico(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}