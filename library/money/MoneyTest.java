package christmas.domain.money;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MoneyTest {

    @ParameterizedTest(name = "정상 {0} 값을 담은 Money 객체가 생성되어야 한다.")
    @ValueSource(ints = {100, 200, 2100000000, 0})
    public void 정상_Money_자식_객체_생성(final int amount) {
        // When
        final Money money = Money.create(amount);

        // Then
        assertThat(money.getAmount()).isEqualTo(amount);
    }

    @ParameterizedTest(name = "음수 {0} 값을 담은 Money 객체를 생성하면 예외 처리해야 한다.")
    @ValueSource(ints = {-1, -2100000000})
    public void Money는_음수값을_가질_수_없음(final int amount) {
        // When && Then
        assertThatThrownBy(() -> Money.create(amount))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "두 Money의 값을 비교하여 {0}이 {1}보다 크거나 같은지는 {2}여야 한다.")
    @CsvSource({
            "100, 100, true", // 같음
            "100, 50, true", // 더 큼
            "100, 150, false" // 더 작음
    })
    public void 더_크거나_같은_값인지_상태값_반환(final int amount, final int compareTo, final boolean expected) {
        // Given
        final Money money = Money.create(amount);

        // When && Then
        assertThat(money.isBiggerOrSameThan(compareTo)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "두 Money의 값을 비교하여 {0}이 {1}보다 작은지는 {2}여야 한다.")
    @CsvSource({
            "100, 150, true", // 더 작음
            "100, 100, false", // 같음
            "100, 50, false" // 더 큼
    })
    public void 더_작은_값인지_상태값_반환(final int amount, final int compareTo, final boolean expected) {
        // Given
        final Money money = Money.create(amount);

        // When && Then
        assertThat(money.isSmallerThan(compareTo)).isEqualTo(expected);
    }

    @ParameterizedTest(name = "두 Money의 값끼리 더해서 {0} + {1} = {2}여야 한다.")
    @CsvSource({
            "100, 50, 150",  // 정상적인 더하기
            "0, 0, 0"        // 0 더하기
    })
    void Money끼리_더하면_새로운_Money객체(final int amount1, final int amount2, final int expected) {
        // Given
        final Money money1 = Money.create(amount1);
        final Money money2 = Money.create(amount2);

        // When
        final Money result = money1.add(money2);

        // Then
        assertThat(money1).isNotSameAs(result);
        assertThat(money2).isNotSameAs(result);
        assertThat(result.getAmount()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "두 Money의 값끼리 빼서 {0} - {1} = {2}여야 한다.")
    @CsvSource({
            "100, 50, 50",   // 정상적인 빼기
            "0, 0, 0"        // 0 빼기
    })
    void Money끼리_빼면_새로운_Money객체(final int amount1, final int amount2, final int expected) {
        // Given
        final Money money1 = Money.create(amount1);
        final Money money2 = Money.create(amount2);

        // When
        final Money result = money1.subtract(money2);

        // Then
        assertThat(money1).isNotSameAs(result);
        assertThat(money2).isNotSameAs(result);
        assertThat(result.getAmount()).isEqualTo(expected);
    }

    @ParameterizedTest(name = "두 Money의 값끼리 나눠서 {0} / {1} = {2}여야 한다.")
    @CsvSource({
            "100, 10, 10",
            "10, 1, 10",
            "0, 100, 0"
    })
    void Money끼리_나누면_새로운_Money객체(final int amount1, final int amount2, final int expected) {
        // Given
        final Money money1 = Money.create(amount1);
        final Money money2 = Money.create(amount2);

        // When
        final Money result = money1.divide(money2);

        // Then
        assertThat(result.getAmount()).isEqualTo(expected);
    }

    @Test
    void Money는_0으로_나눌_수_없음() {
        // Given
        final Money money1 = Money.create(100);
        final Money money2 = Money.create(0);

        // When && Then
        assertThatThrownBy(() -> money1.divide(money2))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "두 Money의 값끼리 나머지를 계산해서 {0} % {1} = {2}여야 한다.")
    @CsvSource({
            "100, 30, 10",   // 정상적인 나머지 연산
            "0, 30, 0"       // 0 나머지 연산
    })
    void Money끼리_나머지_계산하면_새로운_Money객체(final int amount1, final int amount2, final int expected) {
        // Given
        final Money money1 = Money.create(amount1);
        final Money money2 = Money.create(amount2);

        // When
        final Money result = money1.calculateRemainder(money2);

        // Then
        assertThat(result.getAmount()).isEqualTo(expected);
    }

    @Test
    void Money는_나머지_계산시_0으로_나눌_수_없음() {
        // Given
        final Money money1 = Money.create(100);
        final Money money2 = Money.create(0);

        // When && Then
        assertThatThrownBy(() -> money1.calculateRemainder(money2))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
