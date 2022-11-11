package sintatico;

import erro.ErroSintatico;
import token.Token;
import token.TokenTipo;
import java.util.List;

/**
 *
 * @author gustavo e pedro
 */
public class Sintatico {

    private List<Token> tokens;
    private Tree arvore;
    private Token tokenAtual;
    private int indexTokens;

    /**
     * @param tokens
     */
    public Sintatico(List<Token> tokens) {
        this.tokens = tokens;
        this.arvore = new Tree(String);
    }

    /**
     * @throws erro.ErroSintatico
     */
    public void analisar() throws ErroSintatico {
        indexTokens = 0;
        tokenAtual = this.tokens.get(indexTokens);

        verificaExpressao();
    }

    private void verificaExpressao() {
        adicionaNaArvore(RegrasSintatico.EXPRESSAO);

        verificaExpressaoSimples();
    }

    private void verificaExpressaoSimples() {
        adicionaNaArvore(RegrasSintatico.EXPRESSAO_SIMPLES);

        verificaTermo();
        
        if (this.tokenAtual.getTokenTipo() == TokenTipo.ADICAO) {
            adicionaNaArvore(RegrasSintatico.ADICAO);
            proximoToken();
            
            verificaTermo();
        }
        
        if (this.tokenAtual.getTokenTipo() == TokenTipo.DIVISAO) {
            adicionaNaArvore(RegrasSintatico.SUBTRACAO);
            proximoToken();
            
            verificaTermo();
        }
    }

    private void verificaTermo() {
        adicionaNaArvore(RegrasSintatico.TERMO);

        verificaFator();
        
        if (this.tokenAtual.getTokenTipo() == TokenTipo.MULTIPLICACAO) {
            adicionaNaArvore(RegrasSintatico.MULTIPLICACAO);
            proximoToken();
            
            verificaFator();
        }
        
        if (this.tokenAtual.getTokenTipo() == TokenTipo.DIVISAO) {
            adicionaNaArvore(RegrasSintatico.DIVISAO);
            proximoToken();
            
            verificaFator();
        }
    }

    private void verificaFator() {
        if (this.tokenAtual.getTokenTipo() == TokenTipo.IDENTIFICADOR
                || this.tokenAtual.getTokenTipo() == TokenTipo.NUMI
                || this.tokenAtual.getTokenTipo() == TokenTipo.ABREPARENTESES) {
            adicionaNaArvore(RegrasSintatico.FATOR);
        }

        if (this.tokenAtual.getTokenTipo() == TokenTipo.IDENTIFICADOR) {
            verificaVariavel();
        }

        if (this.tokenAtual.getTokenTipo() == TokenTipo.NUMI) {
            adicionaNaArvore(RegrasSintatico.NUMERO);
            proximoToken();
        }

        if (this.tokenAtual.getTokenTipo() == TokenTipo.ABREPARENTESES) {
            adicionaNaArvore(RegrasSintatico.ABREPARENTESES);
            proximoToken();

            verificaExpressao();

            if (this.tokenAtual.getTokenTipo() == TokenTipo.FECHAPARENTESES) {
                adicionaNaArvore(RegrasSintatico.FECHAPARENTESES);
                proximoToken();
            }
        }
    }

    private void verificaVariavel() {
        adicionaNaArvore(RegrasSintatico.VARIAVEL);
        adicionaNaArvore(RegrasSintatico.IDENTIFICADOR);
        proximoToken();
    }

    private void proximoToken() {
        indexTokens++;
        this.tokenAtual = this.tokens.get(indexTokens);

    }
    
    private void adicionaNaArvore(RegrasSintatico regraSintatico) {
        //this.arvore.
    }
}
