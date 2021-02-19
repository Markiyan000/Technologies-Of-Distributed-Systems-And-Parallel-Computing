import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelAlgorithm implements SearchAlgorithm {
    private int[] numbers;

    public ParallelAlgorithm(int[] numbers) {
        this.numbers = numbers;
    }

    @Override
    public int searchTheMostFrequentNumber() {
        final int COUNT_OF_THREADS = Runtime.getRuntime().availableProcessors();
        final int length = numbers.length;
        ExecutorService executorService = Executors.newFixedThreadPool(COUNT_OF_THREADS);
        int mostFrequent = 0, countMostFrequent = 0;
        List<Integer> alreadyChecked = new ArrayList<>();

        int block = length / COUNT_OF_THREADS;
        int endCount = block - 1;

        for (int i = 0; i < length; i++) {
            int begin = i;
            int end = endCount;
            int currentElement = numbers[i];
            if (isElementAlreadyChecked(currentElement, alreadyChecked)) {
                continue;
            }
            alreadyChecked.add(currentElement);
            List<Future<Integer>> results = new ArrayList<>();

            for (int j = 0; j < COUNT_OF_THREADS; j++) {
                if(end >= length) {
                    end = length - 1;
                }
                if(begin >= length) {
                    begin = length - 1;
                }

                Future<Integer> result = executorService.submit(new SearchTask(numbers, currentElement, begin, end));
                results.add(result);
                begin = end + 1;
                end = begin + block - 1;
            }

            int countCurrent = calculateAllEntries(results);
            if (countCurrent > countMostFrequent) {
                mostFrequent = currentElement;
                countMostFrequent = countCurrent;
            }
            endCount++;
        }

        return mostFrequent;
    }

    private int calculateAllEntries(List<Future<Integer>> results) {
        int count = 0;

        for (Future<Integer> currentResult : results) {
            try {
                count += currentResult.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        return count;
    }

    private boolean isElementAlreadyChecked(int element, List<Integer> alreadyChecked) {
        return alreadyChecked.parallelStream().anyMatch(i -> i.equals(element));
    }

    private class SearchTask implements Callable<Integer> {
        private int[] numbers;
        private int currentElement;
        private int begin;
        private int end;

        public SearchTask(int[] numbers, int currentElement, int begin, int end) {
            this.numbers = numbers;
            this.currentElement = currentElement;
            this.begin = begin;
            this.end = end;
        }

        @Override
        public Integer call() throws Exception {
            int countCurrent = 0;
            for (int i = begin; i <= end; i++) {
                if (currentElement == numbers[i]) {
                    countCurrent++;
                }
            }

            return countCurrent;
        }
    }
}
