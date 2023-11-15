package cil.bf.activiteApp.exception;

/**
 * remonte au client les exceptions capturées
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public class AuthentificationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AuthentificationException(String message) {
        super(message);
    }

}
