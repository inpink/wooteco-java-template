package main.validation;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public final class IntegerValidator {

    public static void validateInteger(final String string) {
        if (!IntegerUtil.isInteger(string)) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public static void validateInteger(final String... strings) {
        Arrays.stream(strings)
                .forEach(string -> validateInteger(string));
    }

    public static void validateInteger(List<String> separated) {
        boolean result = separated
                .stream()
                .allMatch( value -> IntegerUtil.isInteger(value));

        if (!result) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public static void validateNotNegative(final int value) {
        if (value < 0) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public static void validateInRange(final int value, final int min, final int max) {
        validateNotSmaller(value, min);
        validateNotBigger(value, max);
    }

    public static void validateNotSmaller(final int value, final int min) {
        if (value < min) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public static void validateNotBigger(final int value, final int max) {
        if (value > max) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public static void validatePlusRange(final int value1, final int value2) {
        final BigInteger a = BigInteger.valueOf(value1);
        final BigInteger b = BigInteger.valueOf(value2);
        final BigInteger result = a.add(b);

        if (result.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0 ||
                result.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    public static void validateSame(final int value1, final int value2) {
        if (value1 != value2) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
}
