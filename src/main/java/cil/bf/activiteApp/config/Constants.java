package cil.bf.activiteApp.config;

/**
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String LOGIN_REGEX = "^(?>[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*)|(?>[_.@A-Za-z0-9-]+)$";
    public static String SYSTEM_ACCOUNT = "system";

    //privileges du systeme
    public static final String ADMIN = "ROLE_ADMIN";
    public static final String USER = "ROLE_USER";

    private Constants() {
    }
}
