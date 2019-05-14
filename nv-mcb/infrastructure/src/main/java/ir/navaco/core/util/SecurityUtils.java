package ir.navaco.core.util;

/**
 * @author Saman Alishiri, samanalishiri@gmail.com
 */
public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static String getCurrentUsername() {
        return "nouser";
    }

    public static Long getCurrentUserId() {
        return 1L;
    }

    public static Long getCurrentOrganizationId() {
        return 1L;
    }
}