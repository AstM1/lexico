package main;

import erro.ErroLexico;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import lexico.Lexico;
import sintatico.Sintatico;

/**
 *
 * @author gustavo e pedro
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

                lexico.mostraTokens();
                lexico.mostraTabelaDeChaves();

                Sintatico sintatico = new Sintatico(lexico.getTokens());
                //sintatico.analisar();
            }
        } catch (ErroLexico e) {
        }
    }

    private static Scanner lerArquivo() {
        Scanner sc = new Scanner(System.in); 
        
        System.out.print("Caminho do arquivo a ser analisado: ");  
        String caminhoArquivo = sc.nextLine(); 
        
        try {
            File file = new File(caminhoArquivo);
            
            return new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
}
