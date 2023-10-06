import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ReadAllLines {

    ExecutorService executor = Executors.newFixedThreadPool(2);

    public void readAllLines(BufferedReader bufferedReader) throws InterruptedException {
        Map<Integer, String> linesMap = new HashMap<>();
        long startTime = System.currentTimeMillis();
        try {
            String line;
            int lineNumber = 1;
            while ((line = bufferedReader.readLine()) != null) {
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
        long endTime = System.currentTimeMillis();
        long elapsedTime = endTime - startTime;
        System.out.println(elapsedTime);
        for (Map.Entry<Integer, String> entry : linesMap.entrySet()) {
            System.out.println("Sor " + entry.getKey() + ": " + entry.getValue());
        }
    }
}
