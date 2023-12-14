package main.util;

public final class StringUtil {

    private StringUtil() {

    }

    public static String removeAllSpaces(final String input) {
        return input.replace(" ", "");
    }

}
