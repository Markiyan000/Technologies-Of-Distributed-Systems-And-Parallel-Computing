package fs;

import java.util.List;

public interface FileProcessor {
    List<Integer> readFromFile();

    void writeToFile(List<Integer> numbers);
}
