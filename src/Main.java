import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        Path path = Path.of("text.txt");
        readSingleLine(path);
        System.out.println("________________");
        readTenLines(path);
        System.out.println("________________");
        readAllLines(path);
    }
    public static void readSingleLine(Path path) throws IOException {
        try {
            Files.walk(path);
        }catch (IOException e){
            e.printStackTrace();
        }

        int lineNumber = 3;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                String filePath = "text.txt";
                BufferedReader reader = new BufferedReader(new FileReader(filePath));
                String line;
                int lineIndex = 0;

                while ((line = reader.readLine()) != null) {
                    lineIndex++;
                    if (lineIndex == lineNumber) {
                        System.out.println("A(z) " + lineNumber + ". sor tartalma: " + line);
                        break;
                    }
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }

    public static void readTenLines(Path path) throws InterruptedException, IOException {
        try {
            Files.walk(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();

        Map<Integer, String> linesMap = new HashMap<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("text.txt"));
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null && lineNumber <= 10) {
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

    public static void readAllLines (Path path) throws InterruptedException {
        try {
            Files.walk(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ExecutorService executor = Executors.newFixedThreadPool(5);

        Map<Integer, String> linesMap = new HashMap<>();

        long startTime = System.currentTimeMillis();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("text.txt"));
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
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
