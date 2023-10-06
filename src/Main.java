import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    BufferedReader bufferedReader = new BufferedReader(new FileReader("text.txt"));

    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        new Main().run();
    }

    public void run() throws IOException, InterruptedException {

        ReadSingleLine readSingleLine = new ReadSingleLine();
        ReadTenLines readTenLines = new ReadTenLines();
        ReadAllLines readAllLines = new ReadAllLines();
        readSingleLine.readSingleLine(bufferedReader);
        readTenLines.readTenLines(bufferedReader);
        readAllLines.readAllLines(bufferedReader);

    }


}

