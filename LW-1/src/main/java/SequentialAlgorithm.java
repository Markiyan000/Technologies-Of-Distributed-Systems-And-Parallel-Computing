import java.util.Arrays;

public class SequentialAlgorithm implements SearchAlgorithm {
    private int[] numbers;

    public SequentialAlgorithm(int[] numbers) {
        this.numbers = numbers;
    }

    public int searchTheMostFrequentNumber() {
        if (numbers == null || numbers.length == 0) {
            throw new RuntimeException("Cannot execute search! Array is empty!");
        }

        int length = numbers.length;
        int[] alreadyChecked = new int[length];
        int k = 0;
        int mostFrequent = numbers[0], countMostFrequent = 1;

        for (int i = 0; i < length; i++) {
            int currentElement = numbers[i];
            int countCurrent = 1;

            if (isElementAlreadyChecked(currentElement, alreadyChecked)) {
                continue;
            }

            alreadyChecked[k++] = currentElement;

            for (int j = i + 1; j < length; j++) {
                if (currentElement == numbers[j]) {
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

    private boolean isElementAlreadyChecked(int element, int []alreadyChecked) {
        return Arrays.stream(alreadyChecked).anyMatch(i -> i == element);
    }
}
