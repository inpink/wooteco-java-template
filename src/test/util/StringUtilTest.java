package test.util;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StringUtilTest {

    @ParameterizedTest(name = "정수 {0}의 모든 공백을 제거하면 {1}다.")
    @CsvSource({
            "'Hello World', 'HelloWorld'",
            "' Java Programming ', 'JavaProgramming'",
            "'123 456 789', '123456789'",
            "'', ''"
    })
    void 문자열의_모든_공백을_제거(final String input, final String expected) {
        // When
        final String result = StringUtil.removeAllSpaces(input);

        // Then
        assertThat(StringUtil.removeAllSpaces(result)).isEqualTo(expected);
    }

}