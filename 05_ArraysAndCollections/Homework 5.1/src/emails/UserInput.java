package emails;

import java.util.Scanner;

public class UserInput {

    public static String getLine() {
        System.out.println("Введите команду или \"EXIT\" для завершения работы.");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().trim();
    }
}
