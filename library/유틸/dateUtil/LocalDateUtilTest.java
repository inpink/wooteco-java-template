package christmas.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;

import java.time.LocalDate;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalDateUtilTest {

    static Stream<Arguments> dateRangeProvider() {
        return Stream.of(
                Arguments.of(LocalDate.of(2023, 12, 15),
                        LocalDate.of(2023, 12, 1),
                        LocalDate.of(2023, 12, 31), true),

                Arguments.of(LocalDate.of(2023, 11, 30),
                        LocalDate.of(2023, 12, 1),
                        LocalDate.of(2023, 12, 31), false),

                Arguments.of(LocalDate.of(2024, 1, 1),
                        LocalDate.of(2023, 12, 1),
                        LocalDate.of(2023, 12, 31), false)
        );
    }

    @ParameterizedTest
    @MethodSource("dateRangeProvider")
    void testIsWithinDateRange(final LocalDate testDate,
                               final LocalDate startDate,
                               final LocalDate endDate,
                               final boolean expected) {
        // When
        final boolean isWithinRange = LocalDateUtil.isWithinDateRange(testDate, startDate, endDate);

        // Then
        assertEquals(expected, isWithinRange);
    }
}
