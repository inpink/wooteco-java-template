package christmas.util;

import static christmas.constants.PatternConstants.THOUSAND_SEPARATOR;

import java.text.DecimalFormat;

public final class StringUtil {

    public static String formatByThousandSeparator(final int number) {
        final DecimalFormat decimalFormat = new DecimalFormat(THOUSAND_SEPARATOR.getPattern());
        return decimalFormat.format(number);
    }


}
