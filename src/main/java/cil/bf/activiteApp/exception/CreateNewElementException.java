package cil.bf.activiteApp.exception;

/**
 * Created by Zak TEGUERA on 14/09/2023.
 * <teguera.zakaria@gmail.com>
 */
public class CreateNewElementException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CreateNewElementException() {
        super("Un nouvel element ne peut pas avoir un id");
    }

}
