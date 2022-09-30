
package erro;

public class ErroLexico extends Exception {

    
    private int linha;
    
    private int coluna;

    private String message;

    
    public ErroLexico(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }
    
    public ErroLexico(int linha, int coluna, String message) {
        this.linha = linha;
        this.coluna = coluna;
        this.message = message;
    }

    
    public int getLinha() {
        return linha;
    }
    
    public int getColuna() {
        return coluna;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
