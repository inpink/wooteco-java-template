package christmas.validation;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateValidatorTest {

    @ParameterizedTest(name = "{0}년 {1}월 {2}일은 옳은 날짜다.")
    @CsvSource({
            "2023, 12, 25",
            "2020, 2, 29",  // 윤년
            "2021, 1, 31",
            "2021, 01, 01"
    })
    public void 달력에_있는_옳은_날짜_검증_통과(final int year, final int month, final int day) {
        // When
        DateValidator.validateExistInCalendar(year, month, day);
    }

    @ParameterizedTest(name = "{0}년 {1}월 {2}일은 옳은 날짜가 아니다.")
    @CsvSource({
            "2023, 12, 0",
            "2023, 12, 32",
            "2023, 2, 30",
            "2021, 4, 31",
            "2020, 11, 31"
    })
    public void 달력에_없는_옳지않은_날짜는_예외처리(final int year, final int month, final int day) {
        // When & Then
        assertThatThrownBy(() -> DateValidator.validateExistInCalendar(year, month, day))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
