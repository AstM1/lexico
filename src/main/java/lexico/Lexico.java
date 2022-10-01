package lexico;

import erro.ErroLexico;
import token.Token;
import token.TokenTipo;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gustavo e pedro
 */
public class Lexico {

    private List<Token> tokens;
    private List<String> tabelaDeChaves;

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
        this.tabelaDeChaves = new ArrayList<>();
        this.tokenAtual = new Token();

        this.linhaAtual = 0;
        this.colunaAtual = 0;
        this.estadoAtual = 0;
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
            this.caracteres = codigo.nextLine().split("");

            this.tokenAtual.setTokenString("");
            this.tokenAtual.setPosicaoInicial(this.colunaAtual);

            for (this.colunaAtual = 0; this.colunaAtual < caracteres.length; this.colunaAtual++) {
                this.caractereAtual = caracteres[this.colunaAtual];
                String aux = this.tokenAtual.getTokenString() + this.caractereAtual;
                this.tokenAtual.setTokenString(aux);

                if (verificaEspaco(this.caractereAtual.trim()) || verificaVazio(this.caractereAtual.trim())) {
                    limpaToken();
                } else {
                    setTipoTokenAtual(this.tokenAtual.getTokenString());
                    if (this.tokenAtual.getTokenTipo().equals(TokenTipo.COMENTARIO)) {
                        break;
                    }
                    setEstado(this.caractereAtual);
                }
            }
        }
    }

    private void setEstado(String cadeiaAtual) {
        if (verificaNumero(cadeiaAtual) && !verificaLetra(caractereAnterior())) {
            if ((verificaEspaco(caractereAnterior()) || verificaVazio(caractereAnterior()))
                    && (verificaEspaco(proximoCaractere())
                    || verificaFimDaLinha()
                    || verificaOperador(proximoCaractere())
                    || (verificaPontuacao(proximoCaractere()) && !proximoCaractere().equals(".")))) {
                this.tokenAtual.setTokenString(cadeiaAtual);
                this.estadoAtual = 3;
            } else if ((verificaEspaco(proximoCaractere())
                    || verificaFimDaLinha()
                    || verificaOperador(proximoCaractere())
                    || (verificaPontuacao(proximoCaractere()) && !proximoCaractere().equals("."))) && !this.tokenAtual.getTokenString().contains(".")) {
                this.estadoAtual = 3;
            } else if (this.estadoAtual == 4 && (verificaEspaco(proximoCaractere())
                    || verificaFimDaLinha()
                    || verificaOperador(proximoCaractere())
                    || verificaPontuacao(proximoCaractere()))) {
                this.estadoAtual = 5;
            }
        } else if (verificaPonto(cadeiaAtual)
                && verificaNumero(caractereAnterior())
                && verificaNumero(proximoCaractere())) {
            this.estadoAtual = 4;
        } else if ((verificaLetra(cadeiaAtual) && !verificaVazio(cadeiaAtual))
                || (verificaNumero(cadeiaAtual) && verificaLetra(caractereAnterior()))) {
            if ((verificaEspaco(caractereAnterior()) || verificaVazio(caractereAnterior()))
                    && (verificaEspaco(proximoCaractere()) || verificaFimDaLinha())) {
                this.tokenAtual.setTokenString(cadeiaAtual);
                this.estadoAtual = 6;
            } else if (verificaEspaco(proximoCaractere())
                    || verificaFimDaLinha()
                    || proximoCaractere().equals(";")
                    || proximoCaractere().equals(":")
                    || verificaOperador(proximoCaractere())
                    || verificaPontuacao(proximoCaractere())) {
                this.estadoAtual = 6;
            }
        } else if (verificaPontuacao(cadeiaAtual)
                && !proximoCaractere().equals("=")) {
            this.estadoAtual = 7;
        } else if (verificaOperador(cadeiaAtual)
                && !proximoCaractere().equals("/")) {
            this.estadoAtual = 8;
        }
        verificaEstado();
    }

    private void verificaEstado() {
        switch (this.estadoAtual) {
            case 2:
                if ((verificaEspaco(proximoCaractere()) || verificaFimDaLinha())) {
                    this.estadoAtual = 3;
                    verificaEstado();
                } else {
                    this.estadoAtual = 0;
                }
                break;
            case 3:
                if (verificaNumero(this.tokenAtual.getTokenString())) {
                    setToken();
                } else {
                    tokenDesconhecido();
                }
                break;
            case 5:
                if (verificaNumero(this.tokenAtual.getTokenString())) {
                    setToken();
                } else {
                    tokenDesconhecido();
                }
                break;
            case 6:
                if (this.tokenAtual.getTokenTipo().equals(TokenTipo.IDENTIFICADOR)
                        && verificaNumeroPrimeiroCaractere(this.tokenAtual.getTokenString())) {
                    tokenDesconhecido();
                } else {
                    setToken();
                }
                break;
            case 7:
                setToken();
                break;
            case 8:
                setToken();
                break;
        }
    }

    private void setToken() {
        this.tokenAtual.setPosicaoFinal(this.colunaAtual);

        this.tokens.add(this.tokenAtual);
        this.adicionaNaTabelaDeChaves();

        this.estadoAtual = 0;
        limpaToken();
    }

    private void adicionaNaTabelaDeChaves() {
        if (this.tokenAtual.getTokenTipo().equals(TokenTipo.IDENTIFICADOR)
                && !this.tabelaDeChaves.contains(this.tokenAtual.getTokenString())) {
            this.tabelaDeChaves.add(this.tokenAtual.getTokenString());
        }
    }

    // ############ FUNÇÕES DE VERIFICAÇÃO ############
    private boolean verificaNumeroPrimeiroCaractere(String str) {
        String[] split = str.split("");
        String caractere = split[0];
        return verificaNumero(caractere);
    }

    private void tokenDesconhecido() {
        ErroLexico e = new ErroLexico(this.linhaAtual, this.colunaAtual, "Erro, caractere \"" + this.caractereAtual + "\" não reconhecido!");
        System.out.println("Erro encontrado na linha: " + e.getLinha() + " e coluna: " + e.getColuna() + " causa: " + e.getMessage());
    }

    private boolean verificaPontuacao(String str) {
        TokenTipo[] tiposToken = TokenTipo.values();
        for (int i = 10; i <= 16; i++) {
            try {
                if (str.equals(tiposToken[i].getValor())) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    private boolean verificaOperador(String str) {
        TokenTipo[] tiposToken = TokenTipo.values();
        for (int i = 0; i < 9; i++) {
            try {
                if (str.equals(tiposToken[i].getValor())) {
                    return true;
                }
            } catch (Exception e) {
            }
        }
        return false;
    }

    private void setTipoTokenAtual(String token) {
        TokenTipo[] tiposToken = TokenTipo.values();
        for (int i = 0; i < tiposToken.length; i++) {
            try {
                if (i < tiposToken.length - 3) {
                    if (token.toUpperCase().equals(tiposToken[i].getValor())) {
                        this.tokenAtual.setTokenTipo(tiposToken[i]);
                        break;
                    }
                } else {
                    if (token.matches(tiposToken[i].getValor())) {
                        this.tokenAtual.setTokenTipo(tiposToken[i]);
                        break;
                    }
                }
            } catch (Exception e) {
            }
        }
        if (this.tokenAtual.getTokenTipo() == null) {
            tokenDesconhecido();
        }
    }

    public void mostraTokens() {
        System.out.println("TOKENS:\n");
        int tamanhoLista = this.tokens.size();
        for (int i = 0; i < tamanhoLista; i++) {
            System.out.print("<" + this.tokens.get(i).getTokenTipo() + ", '" + this.tokens.get(i).getTokenString() + "'>");
            if (i < (tamanhoLista - 1)) {
                System.out.print(", ");
            }
            if ((i + 1) % 5 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n");
    }

    public void mostraTabelaDeChaves() {
        System.out.println("TABELA DE CHAVES: ");
        for (int i = 0; i < this.tabelaDeChaves.size(); i++) {
            System.out.println(i + " -> " + this.tabelaDeChaves.get(i));
        }
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

    private boolean verificaLetra(String str) {
        return str.matches("\\b([a-zA-Z])\\b.*");
    }

    private boolean verificaPonto(String str) {
        return str.equals(".");
    }

    private boolean verificaVazio(String str) {
        return str.equals("");
    }

    private String proximoCaractere() {
        return colunaAtual + 1 <= caracteres.length - 1 ? caracteres[colunaAtual + 1] : "";
    }

    private String caractereAnterior() {
        return colunaAtual > 0 ? caracteres[colunaAtual - 1] : "";
    }

    private void limpaToken() {
        this.tokenAtual = new Token();
    }

    public List<Token> getTokens() {
        return this.tokens;
    }
}
