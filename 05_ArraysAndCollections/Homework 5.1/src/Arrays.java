import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Arrays {

    public static ArrayList<String> deeds = new ArrayList<>() {{
        add("Do exercises");
        add("Do the cleaning");
        add("To wash the dishes");
        add("Make a breakfast");
        add("Take a walk with the dog");
        add("Call a friend");
    }};

    public static String comm(String[] string) {
        return string[0].trim();
    }

    public static int ind(String[] string) {
        return Integer.parseInt(string[1].trim());
    }

    public static String scanDeed(String[] string, int index) {
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < string.length; i++) {
            sb.append(string[i]).append(" ");
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = " ";

        while (!str.equals("EXIT")) {
            System.out.println("Введите команду или \"EXIT\" для завершения: ");
            str = scanner.nextLine();
            String[] commands = str.split("\\s+");

            String command = " ";
            String deed = null;
            int index = -1;

            if (str.matches("^[\\p{Upper}]+")) {
                command = str.trim();

            } else if (str.matches("^[\\p{Upper}]+\\s+\\d+")) {
                command = comm(commands);
                index = ind(commands);

            } else if (str.matches("^[\\p{Upper}]+\\s+\\d+.+")) {
                command = comm(commands);
                index = ind(commands);
                deed = scanDeed(commands, 2);

            } else if (str.matches("^[\\p{Upper}]+.+")) {
                command = comm(commands);
                deed = scanDeed(commands, 1);

            } else {
                System.out.println("Неверная команда.");
            }

            switch (command) {

                case ("LIST"):
                    System.out.println("Список дел:");
                    for (int i = 0; i < deeds.size(); i++) {
                        System.out.println("Работа номер: " + i + " " + deeds.get(i));
                    }
                    break;

                case ("ADD"):
                    if (index > -1 && index < deeds.size()) {
                        deeds.add(index, deed);
                        System.out.println("Добавлена работа \"" + deed + "\" с индексом " + index);
                    } else if (index > deeds.size()) {
                        System.out.println("Укажите индекс не более " + (deeds.size() - 1));
                    } else {
                        deeds.add(deed);
                    }
                    break;

                case ("EDIT"):
                    if (index > -1 && index < deeds.size()) {
                        deeds.set(index, deed);
                        System.out.println("Работа под индексом " + index + " изменена на \"" + deed + "\"");
                    } else {
                        System.out.println("Неверная команда. Укажите индекс редактируемого дела" +
                                " значением не более " + (deeds.size() - 1));
                    }
                    break;

                case ("DELETE"):

                    if (index > -1 && index < deeds.size()) {
                        deeds.remove(index);
                        System.out.println("Работа с индексом " + index + " удалена");
                    } else {
                        System.out.println("Неверная команда. Укажите индекс удаляемого дела" +
                                " значением не более " + (deeds.size() - 1));
                    }
                    break;
                case ("EXIT"):
                    System.out.println("Работа завершена.");
                    break;

                default:
                    System.out.println("Такой команды нет. \nДоступные команды: LIST, ADD, EDIT, DELETE.");
            }
        }
    }
}
