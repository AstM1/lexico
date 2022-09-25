package token;

/**
 *
 * @author gusta
 */
public enum TokenTipo {
    //OPERADORES ARITMETICOS
    ADICAO("+"),
    SUBTRACAO("-"),
    MULTIPLICACAO("*"),
    DIVISAO("/"),
    
//    OPERADORES RELACIONAIS
    IGUAL("="),
    MAIORIGUAL(">="),
    MENORIGUAL("<="),
    MAIOR(">"),
    MENOR("<"),
    DIFERENTE("<>"),
   
//    NUMERO REAL
    NUMR(""),
    
//    NUMERO INTEIRO
    NUMI(""),
    
//    NUMERO PALAVRA RESERVADA
    PROGRAM("PROGRAM"),
    BEGIN("BEGIN"),
    END("END"),
    READ("READ"),
    WRITE("WRITE"),
    IF("IF"),
    THEN("THEN"),
    ELSE("ELSE"),
    FOR("FOR"),
    TO("TO"),
    DO("DO"),
    VAR("VAR"),
    TYPE("TYPE"),
    INTEGER("INTEGER"),
    REAL("REAL"),
    STRING("STRING");

    private final String valor;

    private TokenTipo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
