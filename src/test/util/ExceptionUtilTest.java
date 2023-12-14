package test.util;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class ExceptionUtilTest {

    @Test
    public void 메세지와_IllegalArgumentException발생() {
        // Given
        final String message = "[Error] ";

        // When && Then
        assertThatThrownBy(() -> ExceptionUtil.throwInvalidValueException(message))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[Error] ");
    }

}
