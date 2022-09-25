package token;

/**
 *
 * @author gusta
 */
public class Token {

    private int posicaoInicial;
    private int posicaoFinal;
    private TokenTipo tokenTipo;
    private String tokenString;

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

    public void setPosicaoInicial(int posicaoInicial) {
        this.posicaoInicial = posicaoInicial;
    }

    public void setPosicaoFinal(int posicaoFinal) {
        this.posicaoFinal = posicaoFinal;
    }

    public void setTokenTipo(TokenTipo tokenTipo) {
        this.tokenTipo = tokenTipo;
    }
}
