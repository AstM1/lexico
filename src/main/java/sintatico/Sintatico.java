package sintatico;

import erro.ErroSintatico;
import token.Token;
import java.util.List;

/**
 *
 * @author gustavo e pedro
 */
public class Sintatico {

    private List<Token> tokens;
    private Tree arvore;
    
    /**
     * @param tokens
     */
    public Sintatico(List<Token> tokens) {
        this.tokens = tokens;
        this.arvore = new Tree();
    }

    /**
     * @throws erro.ErroSintatico
     */
    public void analisar() throws ErroSintatico {
        this.tokens.forEach(this.analisaToken());
    }
    
    private analisaToken() {
        
    }
}
