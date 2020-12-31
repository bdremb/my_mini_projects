import java.io.*;

public class MainLoader {
    private static long start;
    private static final String PATH_ONE = "d:/numbersBuffered.txt";
    private static final String PATH_TWO = "d:/numbersBuffered2.txt";
    private static final char[] LETTERS = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

    public static void main(String[] args) throws IOException, InterruptedException {
        start = System.currentTimeMillis();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(PATH_ONE));
       // BufferedWriter bufferedWriter2 = new BufferedWriter(new FileWriter(PATH_TWO));

        Thread thread = new Thread(new Loader(LETTERS, 0, 50, start, bufferedWriter));
        Thread thread2 = new Thread(new Loader(LETTERS, 50, 100, start, bufferedWriter));

        thread.start();
        thread2.start();
        thread.join();
        thread2.join();

        bufferedWriter.flush();
       // bufferedWriter2.flush();
        bufferedWriter.close();
       // bufferedWriter2.close();
    }
}
