import java.util.List;

public class Main {
    private static final String HADOOP_URI = "hdfs://44c1a01e8ad3:8020";

    public static void main(String[] args) throws Exception {
        FileAccess fileAccess = new FileAccess(HADOOP_URI);

        fileAccess.create("newFile2.txt");
        fileAccess.create("newDirectory2/");
        fileAccess.create("newDirectory2/qwerty2.txt");
        fileAccess.append("newDirectory2/qwerty2.txt", "ABCDEFGHIGKLMNOPQRSTUVWXYZ");
        List<String> list = fileAccess.list("");
        for (String s : list) {
            System.out.println(s);
        }
        System.out.println(fileAccess.read("newDirectory2/qwerty2.txt"));
        fileAccess.delete("newDirectory/");
        fileAccess.close();
    }
}
