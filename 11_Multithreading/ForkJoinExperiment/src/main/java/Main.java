import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static final String URL_ADDRESS = "https://skillbox.ru/";
    public static final String FILE_NAME = "src/main/resources/" +
            URL_ADDRESS.substring(URL_ADDRESS.indexOf("/") + 1, URL_ADDRESS.indexOf(".")) + ".txt";

    public static void main(String[] args) {
        NodeSite node = new NodeSite(URL_ADDRESS);
        List<String> result = new ForkJoinPool().invoke(new CustomRecursiveTask(node));
        writeToFile(result);
    }

    public static void writeToFile(List<String> result) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
            for (String string : result) {
                bufferedWriter.write(string);
                bufferedWriter.newLine();
            }
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
