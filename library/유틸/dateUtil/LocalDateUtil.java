package christmas.util;

import java.time.LocalDate;

public class LocalDateUtil {

    public static boolean isWithinDateRange(final LocalDate date,
                                            final LocalDate startDate,
                                            final LocalDate endDate) {
        return !date.isBefore(startDate) && !date.isAfter(endDate);
    }
}
