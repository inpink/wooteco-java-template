package christmas.util;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.assertThat;

public class StringUtilTest {

    @ParameterizedTest(name = "정수 {0}는 천단위 구분자 문자열로 {1}다.")
    @CsvSource({
            "'1000', '1,000'",
            "'1000000', '1,000,000'",
            "'123456789', '123,456,789'",
            "'0', '0'"
    })
    void 천단위_구분자_문자열_생성(final int number, final String expected) {
        // When
        final String result = StringUtil.formatByThousandSeparator(number);

        // Then
        assertThat(result).isEqualTo(expected);
    }



}