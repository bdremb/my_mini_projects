import java.util.Scanner;
import java.util.TreeSet;


public class EmailSet {
    private static TreeSet<String> emails = new TreeSet<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = " ";
        System.out.println("Введите команду или \"EXIT\" для завершения работы.");

        while (!str.equals("EXIT")) {
            str = scanner.nextLine().trim();
            if (str.matches("ADD\\s+\\w+@\\w+\\.\\w+\\s?+$")) {

                String mail = str.substring(str.indexOf(" ")).trim();
                if (emails.contains(mail.toLowerCase())) {
                    System.out.println("Такой адрес уже существует в базе. Введите другой.");
                } else {
                    emails.add(mail.toLowerCase());
                    System.out.println("Новый адрес успешно добавлен.");
                }

            } else if (str.matches("LIST\\s?+")) {

                if (emails.isEmpty()) {
                    System.out.println("Список адресов пуст. Добавьте новый адрес.");

                } else {
                    for (String email : emails) {
                        System.out.println(email);
                    }
                }

            } else if (str.matches("EXIT\\s?+")) {

                System.out.println("Работа завершена.");

            } else {

                System.out.println("Неверная команда или неправильный формат электронного адреса. \nПопробуйте еще.");

            }
        }
    }
}

