package algorithm;

import java.util.ArrayList;
import java.util.List;

public class SequentialAlgorithm implements SearchAlgorithm {
    private List<Integer> numbers;

    public SequentialAlgorithm(List<Integer> numbers) {
        this.numbers = numbers;
    }

    public int searchTheMostFrequentNumber() {
        if (numbers == null || numbers.size() == 0) {
            throw new RuntimeException("Cannot execute search! List is empty!");
        }

        int length = numbers.size();
        List<Integer> alreadyChecked = new ArrayList<>();
        int k = 0;
        int mostFrequent = numbers.get(0), countMostFrequent = 1;

        for (int i = 0; i < length; i++) {
            int currentElement = numbers.get(i);
            int countCurrent = 1;

            if (isElementAlreadyChecked(currentElement, alreadyChecked)) {
                continue;
            }

            alreadyChecked.add(currentElement);

            for (int j = i + 1; j < length; j++) {
                if (currentElement == numbers.get(j)) {
                    countCurrent++;
                }
            }

            if (countCurrent > countMostFrequent) {
                mostFrequent = currentElement;
                countMostFrequent = countCurrent;
            }
        }

        return mostFrequent;
    }

    private boolean isElementAlreadyChecked(int element, List<Integer> alreadyChecked) {
        return alreadyChecked.parallelStream().anyMatch(i -> i.equals(element));
    }
}
