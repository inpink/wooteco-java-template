package main.util;

public final class IntegerUtil {

    private IntegerUtil() {

    }

    public static boolean isInteger(final String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

}
