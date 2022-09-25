package lexico;

import erro.ErroLexico;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import token.PalavraReservada;
import token.Token;

/**
 *
 * @author gustavo e pedro
 */
public class Lexico {

    /**
     * Lista dos tokens gerados a partir da entrada
     */
    private List<Token> tokens;
    private Integer linhaAtual;
    private Integer colunaAtual;
    private Integer estadoAtual;
    private String caractereAtual;
    private Token tokenAtual;
    private String[] caracteres;

    /**
     * Construtor: Inicializa a lista de tokens com um arraylist vazio e as
     * posições de linha e coluna
     */
    public Lexico() {
        this.tokens = new ArrayList<>();
        this.linhaAtual = 0;
        this.estadoAtual = 0;
        this.tokenAtual = new Token();

    }

    /**
     * Transforma o codigo de entrada em tokens
     *
     * @param codigo a ser analisado
     * @throws ErroLexico se houver erro lexico no codigo
     *
     */
    public void analisar(Scanner codigo) throws ErroLexico {
        while (codigo.hasNextLine()) {
            this.linhaAtual++;
            this.caracteres = codigo.next().split("");
            for (this.colunaAtual = 0; this.colunaAtual < caracteres.length; this.colunaAtual++) {
                this.caractereAtual = caracteres[this.colunaAtual];
                String aux = this.tokenAtual.getTokenString() + this.caractereAtual;
                this.tokenAtual.setTokenString(aux);

                verificaTipo(this.caractereAtual);
            }
        }
    }

    private void verificaTipo(String str) {
        if (verificaEspaco(str)) {
            limpaToken();
        }
        if (verificaNumero(str)) {
            if ((verificaEspaco(caractereAnterior()) || verificaVazio(caractereAnterior()))
                    && (verificaEspaco(proximoCaractere()) || verificaFimDaLinha())) {
                this.tokenAtual.setTokenString(str);
                this.estadoAtual = 3;
            } else if ((verificaEspaco(proximoCaractere()) || verificaFimDaLinha()) && !this.tokenAtual.getTokenString().contains(".")) {
                this.estadoAtual = 3;
            } else if (this.estadoAtual == 4 && (verificaEspaco(proximoCaractere()) || verificaFimDaLinha())) {
                this.estadoAtual = 5;
            }
        } else if (verificaPonto(str) && verificaNumero(caractereAnterior()) && verificaNumero(proximoCaractere())) {
            this.estadoAtual = 4;
        } else if (verificaLetra(str) && !verificaVazio(str)) {
            if ((verificaEspaco(caractereAnterior()) || verificaVazio(caractereAnterior()))
                    && (verificaEspaco(proximoCaractere()) || verificaFimDaLinha())) {
                this.tokenAtual.setTokenString(str);
                this.estadoAtual = 6;
            } else if ((verificaEspaco(proximoCaractere()) || verificaFimDaLinha()) && !verificaNumero(caractereAnterior())) {
                this.estadoAtual = 6;
            }
        } else if (verificaPontuacao(str) && !proximoCaractere().equals("=")) {
            this.estadoAtual = 7;
        } else if (verificaOperador(str)) {
            this.estadoAtual = 8;
        }
        verificaEstado();
    }

    private boolean verificaFimDaLinha() {
        return this.colunaAtual >= caracteres.length - 1;
    }

    private boolean verificaNumero(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    private boolean verificaEspaco(String str) {
        return str.equals(" ") || str.equals("\n");
    }

    private boolean isReservedWord(String str) {
        PalavraReservada[] values = PalavraReservada.values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].equals(str)) { //  não funciona precisa pegar o .getValor()
                return true;
            }
        }
        return true;
    }

    private boolean verificaPontuacao(String str) {
        return true;
    }

    private boolean verificaOperador(String str) {
        return true;
    }

    private void verificaEstado() {
    }

    private boolean verificaLetra(String str) {
        return true;
    }

    private boolean verificaPonto(String str) {
        return str.equals(".");
    }

    private boolean verificaVazio(String str) {
        return str.equals("");
    }

    private String proximoCaractere() {
        return colunaAtual + 1 <= caracteres.length ? caracteres[colunaAtual + 1] : "";
    }

    private String caractereAnterior() {
        return colunaAtual > 0 ? caracteres[colunaAtual - 1] : "";
    }

    private void limpaToken() {
        this.tokenAtual = new Token();
    }

    /**
     * Returns a sequence of tokens
     *
     * @return list of tokens
     */
    public List<Token> getTokens() {
        return this.tokens;
    }

    /**
     * Creates map from token types to its regular expressions
     *
     */
    private void launchRegEx() {
//        regEx.put(TokenTipo.BlockComment, "(/\\*.*?\\*/).*");
//        regEx.put(TokenTipo.LineComment, "(//(.*?)[\r$]?\n).*");
//        regEx.put(TokenTipo.WhiteSpace, "( ).*");
//        regEx.put(TokenTipo.OpenBrace, "(\\().*");
//        regEx.put(TokenTipo.CloseBrace, "(\\)).*");
//        regEx.put(TokenTipo.Semicolon, "(;).*");
//        regEx.put(TokenTipo.Comma, "(,).*");
//        regEx.put(TokenTipo.OpeningCurlyBrace, "(\\{).*");
//        regEx.put(TokenTipo.ClosingCurlyBrace, "(\\}).*");
//        regEx.put(TokenTipo.DoubleConstant, "\\b(\\d{1,9}\\.\\d{1,32})\\b.*");
//        regEx.put(TokenTipo.IntConstant, "\\b(\\d{1,9})\\b.*");
//        regEx.put(TokenTipo.Void, "\\b(void)\\b.*");
//        regEx.put(TokenTipo.Int, "\\b(int)\\b.*");
//        regEx.put(TokenTipo.Double, "\\b(int|double)\\b.*");
//        regEx.put(TokenTipo.Tab, "(\\t).*");
//        regEx.put(TokenTipo.NewLine, "(\\n).*");
//        regEx.put(TokenTipo.Public, "\\b(public)\\b.*");
//        regEx.put(TokenTipo.Private, "\\b(private)\\b.*");
//        regEx.put(TokenTipo.False, "\\b(false)\\b.*");
//        regEx.put(TokenTipo.True, "\\b(true)\\b.*");
//        regEx.put(TokenTipo.Null, "\\b(null)\\b.*");
//        regEx.put(TokenTipo.Return, "\\b(return)\\b.*");
//        regEx.put(TokenTipo.New, "\\b(new)\\b.*");
//        regEx.put(TokenTipo.Class, "\\b(class)\\b.*");
//        regEx.put(TokenTipo.If, "\\b(if)\\b.*");
//        regEx.put(TokenTipo.Else, "\\b(else)\\b.*");
//        regEx.put(TokenTipo.While, "\\b(while)\\b.*");
//        regEx.put(TokenTipo.Static, "\\b(static)\\b.*");
//        regEx.put(TokenTipo.Point, "(\\.).*");
//        regEx.put(TokenTipo.Plus, "(\\+{1}).*");
//        regEx.put(TokenTipo.Minus, "(\\-{1}).*");
//        regEx.put(TokenTipo.Multiply, "(\\*).*");
//        regEx.put(TokenTipo.Divide, "(/).*");
//        regEx.put(TokenTipo.EqualEqual, "(==).*");
//        regEx.put(TokenTipo.Equal, "(=).*");
//        regEx.put(TokenTipo.ExclameEqual, "(\\!=).*");
//        regEx.put(TokenTipo.Greater, "(>).*");
//        regEx.put(TokenTipo.Less, "(<).*");
//        regEx.put(TokenTipo.Identifier, "\\b([a-zA-Z]{1}[0-9a-zA-Z_]{0,31})\\b.*");
    }

}
