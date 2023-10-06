import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadSingleLine {

    ExecutorService executor = Executors.newSingleThreadExecutor();

    public void readSingleLine(BufferedReader bufferedReader) throws IOException {
        int lineNumber = 3;
        executor.submit(() -> {
            try {
                String line;
                int lineIndex = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    lineIndex++;
                    if (lineIndex == lineNumber) {
                        System.out.println("A(z) " + lineNumber + ". sor tartalma: " + line);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        executor.shutdown();
    }
}
