package token;

/**
 *
 * @author gusta
 */
public class Token {

    /**
     * The beginning index of this token in the input
     */
    private int posicaoInicial;

    /**
     * The ending index of token in the input
     */
    private int posicaoFinal;

    /**
     * Type(category) of token
     */
    private TokenTipo tokenTipo;

    /**
     * String of characters for this token
     */
    private String tokenString;

    /**
     * Constructs new {@code Token} object with specified parameters.
     *
     * @param posicaoInicial the beginning index of this token in the input,
     * inclusive
     * @param posicaoFinal the ending index of token in the input, exclusive
     * @param tokenString string of characters
     * @param tokenTipo type of token
     */
    public Token(int posicaoInicial, int posicaoFinal, String tokenString, TokenTipo tokenTipo) {
        this.posicaoInicial = posicaoInicial;
        this.posicaoFinal = posicaoFinal;
        this.tokenTipo = tokenTipo;
        this.tokenString = tokenString;
    }

    public Token() {
    }

    public int getBegin() {
        return posicaoInicial;
    }

    public int getEnd() {
        return posicaoFinal;
    }
    
    public String getTokenString() {
        return tokenString;
    }

    public TokenTipo getTokenTipo() {
        return tokenTipo;
    }

    public void setTokenString(String tokenString) {
        this.tokenString = tokenString;
    }
}
