package fs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileProcessorImpl implements FileProcessor {
    private String fileName;

    public FileProcessorImpl(String fileName) {
        this.fileName = fileName;
    }

    public List<Integer> readFromFile() {
        Path pathToFile = getPath();
        try {
            Stream<String> lines = Files.lines(pathToFile);
            return mapStringsToIntegers(lines);
        } catch (IOException e) {
            throw new RuntimeException("Exception has occurred during reading from file ---> " + fileName);
        }
    }

    public void writeToFile(List<Integer> numbers) {
        Path pathToFile = getPath();
        String content = mapIntegersToString(numbers);
        try {
            Files.write(pathToFile, content.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Exception has occurred during writing to file ---> " + fileName);
        }
    }

    private Path getPath() {
        return Paths.get(fileName);
    }

    private String mapIntegersToString(List<Integer> numbers) {
        return numbers.parallelStream().map(String::valueOf).collect(Collectors.joining("\n"));
    }

    private List<Integer> mapStringsToIntegers(Stream<String> lines) {
        return lines.map(Integer::parseInt).collect(Collectors.toList());
    }
}
