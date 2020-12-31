import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;
import java.util.stream.Stream;

public class MainCopy {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите путь папки, которую надо скопировать:");
            Path fromDir = Paths.get(scanner.nextLine());
            if (Files.notExists(fromDir)) {
                throw new FileNotFoundException("папки: " + fromDir + " не существует");
            }
            System.out.println("Введите путь папки, куда копировать:");
            Path toDir = Paths.get(scanner.nextLine());
            copyDirectory(fromDir, toDir);
            System.out.println("Операция успешно завершена.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copyDirectory(Path fromPath, Path toPath) {
        try {
            if (Files.isDirectory(fromPath)) {
                if (Files.notExists(toPath)) {
                    Files.createDirectories(toPath);
                    System.out.println("Создание папки : " + toPath);
                }
                try (Stream<Path> paths = Files.list(fromPath)) { //получаем stream
                    paths.forEach(p -> copyDirectory(p, toPath.resolve(fromPath.relativize(p))));
                }
            } else {
                Files.copy(fromPath, toPath, StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
