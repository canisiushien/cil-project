package cil.bf.activiteApp.exception;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super("Email déjà utilisée !");
    }
}
