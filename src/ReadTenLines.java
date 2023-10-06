import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReadTenLines {

    ExecutorService executor = Executors.newSingleThreadExecutor();

    public void readTenLines(BufferedReader bufferedReader) throws InterruptedException, IOException {
        Map<Integer, String> linesMap = new HashMap<>();
        try {
            String line;
            int lineNumber = 1;
            while ((line = bufferedReader.readLine()) != null && lineNumber <= 10) {
                int actualLineNumber = lineNumber;
                String finalLine = line;
                executor.submit(() -> {
                    linesMap.put(actualLineNumber, finalLine);
                });
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
            executor.awaitTermination(2, TimeUnit.SECONDS);
        }
        for (Map.Entry<Integer, String> entry : linesMap.entrySet()) {
            System.out.println("Sor " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
