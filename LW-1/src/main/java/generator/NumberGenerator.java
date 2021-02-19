package generator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {

    public static List<Integer> generateValues(int size, int bound) {
        List<Integer> numbers = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            numbers.add(random.nextInt(bound));
        }

        return numbers;
    }
}
