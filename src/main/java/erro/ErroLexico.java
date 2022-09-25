/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package erro;

/**
 *
 * @author gusta
 */
public class ErroLexico extends Exception {

    /**
     * Position in the input source(lexer) or the number of token(parser), where
     * the error occured
     */
    private int errorPosition;

    /**
     * The detail message
     */
    private String message;

    /**
     * Creates {@code ErroLexico} object with specified error position
     *
     * @param errorPosition position of the error
     */
    public ErroLexico(int errorPosition) {
        this.errorPosition = errorPosition;
    }

    /**
     * Creates {@code ErroLexico} object with specified error position and
     * message
     *
     * @param message detailed message
     * @param errorPosition position of the error
     */
    public ErroLexico(String message, int errorPosition) {
        this.errorPosition = errorPosition;
        this.message = message;
    }

    /**
     * Returns error's position in the input
     *
     * @return error's position
     */
    public int getErrorPosition() {
        return errorPosition;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
