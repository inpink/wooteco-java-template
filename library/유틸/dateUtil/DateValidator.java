package christmas.validation;

import static christmas.messages.ErrorMessages.INVALID_DATE;

import christmas.util.ExceptionUtil;
import java.time.DateTimeException;
import java.time.LocalDate;

public final class DateValidator {

    public static void validateExistInCalendar(final int year,
                                               final int month,
                                               final int date) {
        try {
            LocalDate.of(year, month, date);
        } catch (DateTimeException e) {
            ExceptionUtil.throwInvalidValueException(INVALID_DATE.getMessage());
        }
    }
}
