import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileAccess implements AutoCloseable {
    private FileSystem hdfs;
    private final String rootPath;

    public FileAccess(String rootPath) {
        Configuration configuration = new Configuration();
        configuration.set("dfs.client.use.datanode.hostname", "true");
        System.setProperty("HADOOP_USER_NAME", "root");
        try {
            hdfs = FileSystem.get(new URI(rootPath), configuration);
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        this.rootPath = rootPath;
    }

    public void create(String path) throws IOException {
        Path fullPath = new Path(rootPath + "/" + path);
        if (!hdfs.exists(fullPath)) {
            if (path.matches(".+/$")) {
                hdfs.mkdirs(fullPath);
            } else {
                hdfs.createNewFile(fullPath);
            }
        }
    }

    public void append(String path, String content) {
        Path file = new Path(rootPath + "/" + path);
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(hdfs.append(file)));) {
            bw.write(content);
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read(String path) {
        Path file = new Path(rootPath + "/" + path);
        StringBuilder sb = new StringBuilder();
        try (BufferedReader bf = new BufferedReader(new InputStreamReader(hdfs.open(file)))) {
            while (bf.ready()) {
                sb.append(bf.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public void delete(String path) throws IOException {
        Path deletePath = new Path(rootPath + "/" + path);
        if (hdfs.exists(deletePath)) {
            hdfs.delete(deletePath, true);
        }
    }

    public boolean isDirectory(String path) throws IOException {
        return hdfs.isDirectory(new Path(rootPath + "/" + path));
    }

    public List<String> list(String path) throws IOException {
        Path file = new Path(rootPath + "/" + path);
        return Arrays.stream(hdfs.listStatus(file))
                .map(fl -> fl.getPath()
                        .toString()
                        .substring(fl.getPath().toString().lastIndexOf("/") + 1))
                .collect(Collectors.toList());
    }

    @Override
    public void close() throws Exception {
        hdfs.close();
    }
}
