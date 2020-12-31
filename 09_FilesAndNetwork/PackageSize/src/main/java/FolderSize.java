import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class FolderSize {
    private static Logger logger;
    private static final Marker PACKAGE_PATH_ENTER = MarkerManager.getMarker("PACKAGE");
    private static final Marker ERROR_PATH = MarkerManager.getMarker("ERROR");

    public static void main(String[] args) {
        logger = LogManager.getRootLogger();
        Scanner scan = new Scanner(System.in);
        File pathPackage = null;

        for (; ; ) {
            System.out.println("Введите путь до папки:");

            try {
                pathPackage = new File(scan.nextLine());
                double folderSize;
                if (!pathPackage.exists()) {
                    System.out.println("Такого пути не существует: '" + pathPackage + "'");
                    throw new FileNotFoundException();
                } else {
                    folderSize = getResult(pathPackage);
                    logger.warn(PACKAGE_PATH_ENTER, "введен путь до папки: " + pathPackage);
                }
                String message = "Размер папки \"" + pathPackage + "\" составляет ";
                System.out.println(message + readableFileSize(folderSize));

            } catch (FileNotFoundException ex) {
                logger.warn(ERROR_PATH, pathPackage + " Такого пути не существует.");
                ex.getStackTrace();
            } catch (Exception ex) {
                logger.warn(ERROR_PATH, ex.getStackTrace());
                ex.getStackTrace();
            }
        }
    }

    private static double getResult(File file) {  //метод с рекурсией
        double size = 0;
        File[] files = file.listFiles();
        for (File value : files) {
            if (value.isFile()) {
                size += value.length();
            } else {
                size += getResult(value);
            }
        }
        return size;
    }

    public static String readableFileSize(double size) {
        if (size <= 0) {
            return "0";
        }
        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups))
                + " " + units[digitGroups];
    }

}
