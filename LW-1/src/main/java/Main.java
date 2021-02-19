import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        final int LENGTH = 2000000;
        int []numbers = new int[LENGTH];
        Random random = new Random();

        for(int i = 0; i < LENGTH; i++) {
            numbers[i] = random.nextInt(5000);
        }

        SearchAlgorithm searchAlgorithm = new SequentialAlgorithm(numbers);
        long startTimeSequential = System.currentTimeMillis();
        int resultSequential = searchAlgorithm.searchTheMostFrequentNumber();
        long spentTimeSequential = System.currentTimeMillis() - startTimeSequential;
        System.out.println("Sequential algorithm: result ---> " + resultSequential + ", spent time ---> " + TimeUnit.MILLISECONDS.toSeconds(spentTimeSequential) + "s.");

        searchAlgorithm = new ParallelAlgorithm(numbers);
        long startTimeParallel = System.currentTimeMillis();
        int resultParallel = searchAlgorithm.searchTheMostFrequentNumber();
        long spentTimeParallel = System.currentTimeMillis() - startTimeParallel;
        System.out.println("Parallel algorithm: result ---> " + resultParallel + ", spent time ---> " + TimeUnit.MILLISECONDS.toSeconds(spentTimeParallel) + "s.");

    }
}
