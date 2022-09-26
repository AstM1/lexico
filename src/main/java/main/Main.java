package main;

import erro.ErroLexico;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lexico.Lexico;
import token.OperadorAritmetico;

/**
 *
 * @author gustavo, pedro
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner = lerArquivo();
        try {
            if (scanner != null) {
                Lexico lexico = new Lexico();
                lexico.analisar(scanner);
            }
        } catch (ErroLexico e) {
        }
    }

    private static Scanner lerArquivo() {
        Scanner s = null;
        try {
            File f = new File("arquivos/codigo.txt");
//            File f = new File("/Users/pedrochagas/Documents/Faculdade/5ºAno/Compiladores/TrabalhoLexico/lexico/arquivos/codigo.txt");
            return new Scanner(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
