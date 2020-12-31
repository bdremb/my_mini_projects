import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int newWidth = 300;
    private static final String srcFolder = "D:\\src";
    private static final String dstFolder = "D:\\dst";
    private static List<File[]> arrayFiles;
    private static File[] files;

    public static void main(String[] args) {
        int cores = Runtime.getRuntime().availableProcessors();
        arrayFiles = new ArrayList<>();
        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();

        files = srcDir.listFiles();

        int remainder = files.length % cores;
        int parts = files.length / cores;
        int sizeArray = parts + 1;
        int firstIndex = 0;

        if (remainder == 0) {
            for (int i = 0; i < cores; i++) {
                firstIndex += getArrayFiles(firstIndex, parts);
            }
        } else {
            for (int i = 0; i < remainder; i++) {
                firstIndex += getArrayFiles(firstIndex, sizeArray);
            }
            for (int i = 0; i < cores - remainder; i++) {
                firstIndex += getArrayFiles(firstIndex, parts);
            }
        }

        arrayFiles.forEach(file -> new Thread(new ImageResizer(newWidth, file, dstFolder, start)).start());//
    }

    private static int getArrayFiles(int firstIndexOfFiles, int size) {
        File[] fileToList = new File[size];
        System.arraycopy(files, firstIndexOfFiles, fileToList, 0, fileToList.length);
        arrayFiles.add(fileToList);
        return fileToList.length;
    }
}