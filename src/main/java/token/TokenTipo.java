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
    
    //OPERADORES RELACIONAIS
    IGUAL("="),
    MAIORIGUAL(">="),
    MENORIGUAL("<="),
    MAIOR(">"),
    MENOR("<"),
    DIFERENTE("<>"),
    
    //PONTUAÇÃO
    PONTO("."),
    PONTOVIRGULA(";"),
    ATRIBUICAO(":="),
    DOISPONTOS(":"),
    ABREPARENTESES("("),
    FECHAPARENTESES(")"),
    
    //PALAVRA RESERVADA
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
    STRING("STRING"),
   
    //NUMERO REAL
    NUMR("-?\\d+\\.\\d+"),
    
    //NUMERO INTEIRO
    NUMI("-?\\d+"),
    
    //IDENTIFICADOR
    IDENTIFICADOR("\\b([a-zA-Z]{1}[0-9a-zA-Z_]{0,31})\\b.*");
    
    private final String valor;

    private TokenTipo(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }
}
