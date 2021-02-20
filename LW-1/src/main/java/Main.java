import algorithm.ParallelAlgorithm;
import algorithm.SearchAlgorithm;
import algorithm.SequentialAlgorithm;
import fs.FileProcessor;
import fs.FileProcessorImpl;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        final String fileName = "C:\\Users\\marki\\IdeaProjects" +
            "\\Technologies-Of-Distributed-Systems-And-Parallel-Computing\\LW-1\\src\\main\\resources\\numbers.txt";
        FileProcessor fileProcessor = new FileProcessorImpl(fileName);
        List<Integer> numbers = fileProcessor.readFromFile();
        SearchAlgorithm[] algorithms = {new SequentialAlgorithm(numbers), new ParallelAlgorithm(numbers)};

        for (SearchAlgorithm searchAlgorithm : algorithms) {
            long start = System.currentTimeMillis();
            int result = searchAlgorithm.searchTheMostFrequentNumber();
            long duration = System.currentTimeMillis() - start;
            System.out.println(searchAlgorithm.getClass()
                .getSimpleName() + ": result ---> " + result + ", duration ---> " + TimeUnit.MILLISECONDS
                .toSeconds(duration) + "s.");
        }
    }
}
