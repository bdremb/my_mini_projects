import java.io.*;
import java.util.Scanner;

public class SecondMainCopy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите путь папки, которую надо скопировать:");
            File fromDir = new File(scanner.nextLine());
            if (!fromDir.exists()) {
                throw new FileNotFoundException("папки: " + fromDir + " не существует");
            }
            System.out.println("Введите путь папки, куда копировать:");
            File toDir = new File(scanner.nextLine());
            copyDirectory(fromDir, toDir);
            System.out.println("Операция успешно завершена.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyDirectory(File from, File to) {
        if (from.isDirectory()) {
            if (!to.exists()) {
                to.mkdir();
            }
            String[] files = from.list();
            if (files == null) {
                return;
            }
            for (String file : files) {
                File fromFile = new File(from, file);  //
                File toFile = new File(to, file);
                copyDirectory(fromFile, toFile);
            }
        } else {
            try (InputStream in = new FileInputStream(from);
                 OutputStream out = new FileOutputStream(to)) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
