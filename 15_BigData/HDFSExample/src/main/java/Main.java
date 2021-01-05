import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URI;

public class Main {
    private static final String HADOOP_URI = "hdfs://44c1a01e8ad3:8020";
    private static String symbols = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) throws Exception {
//        FileAccess fileAccess = new FileAccess(HADOOP_URI);
//
//        fileAccess.create("newFile2.txt");
//        fileAccess.create("newDirectory2/");
//        fileAccess.create("newDirectory2/qwerty2.txt");
//        fileAccess.append("newDirectory2/qwerty2.txt", "qwrqwrqvsfgbrsDSSDDwrqwrwafdsafsaqqrwqwrqwrwqrqrwqw");
//        List<String> list = fileAccess.list("");
//        for (String s : list) {
//            System.out.println(s);
//        }
//        System.out.println(fileAccess.read("newDirectory2/qwerty2.txt"));
//        fileAccess.delete("newDirectory/");
//        fileAccess.close();

        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");

        FileSystem hdfs = FileSystem.get(
                new URI("hdfs://a55c85d51062:8020"), configuration
        );
        Path file = new Path("hdfs://a55c85d51062:8020/test/file.txt");

        if (hdfs.exists(file)) {
            hdfs.delete(file, true);
        }

        OutputStream outputStream = hdfs.create(file);
        BufferedWriter bufferedWriter = new BufferedWriter(
                new OutputStreamWriter(outputStream, "UTF-8")
        );


        InputStream fileInputStream = new FileInputStream("D:/startText.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
        while (bufferedReader.ready()){
            bufferedWriter.write(bufferedReader.readLine());
        }

        fileInputStream.close();
        bufferedReader.close();

        bufferedWriter.flush();
        bufferedWriter.close();
        hdfs.close();

    }

//    private static String getRandomWord()
//    {
//        StringBuilder builder = new StringBuilder();
//        int length = 2 + (int) Math.round(10 * Math.random());
//        int symbolsCount = symbols.length();
//        for(int i = 0; i < length; i++) {
//            builder.append(symbols.charAt((int) (symbolsCount * Math.random())));
//        }
//        return builder.toString();
//    }
}
