package main.util;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomUtil {

    private RandomUtil() {

    }

    public static int pickNumberInList(final List<Integer> numbers) {
        return Randoms.pickNumberInList(numbers);
    }

    public static int pickNumberInRange(final int startInclusive, final int endInclusive) {
        return Randoms.pickNumberInRange(startInclusive, endInclusive);
    }

    public static List<Integer> pickUniqueNumbersInRange(
            final int startInclusive,
            final int endInclusive,
            final int count
    ) {
        return new ArrayList<>(Randoms.pickUniqueNumbersInRange(startInclusive, endInclusive, count));
    }

    public static List<Integer> pickUniqueSortedNumbersInRange(
            final int startInclusive,
            final int endInclusive,
            final int count
    ) {
        List<Integer> randomNumbers = pickUniqueNumbersInRange(startInclusive, endInclusive, count);
        Collections.sort(randomNumbers);

        return randomNumbers;
    }

    public static List<String> shuffle(final List<String> elements) {
        return new ArrayList<>(Randoms.shuffle(elements));
    }

}
