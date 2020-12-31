import java.util.*;

public class Phonebook {
    public static void main(String[] args) {
        HashMap<Long, String> phonebook = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        String data = " ";

        command();

        while (!data.equals("EXIT")) {

            data = scanner.nextLine();

            if (data.matches("LIST")) {

                System.out.println("\nСписок абонентов:");

                printListSortByName(phonebook);
                command();

            } else if (data.matches("\\D+")) { // все кроме цифр  value

                phonePrintByName(phonebook, data, scanner);
                command();

            } else if (data.matches("\\+?\\d+")) {    //цифры key

                phonePrintByNumber(phonebook, data, scanner);
                command();

            }

        }
    }


    private static void phonePrintByName(Map<Long, String> map, String name, Scanner scan) {
        long num = 0;
        if (map.containsValue(name)) {
            for (Map.Entry<Long, String> phone : map.entrySet()) {
                if (phone.getValue().equals(name)) {
                    num = phone.getKey();
                }
            }
            System.out.println("Имя абонента: " + name + ". Номер телефона: +" + num);
        } else {
            System.out.println("Абонента с таким именем не существует. Введите номер телефона:");
            long number = scan.nextLong();
            map.put(number, name);
            System.out.println("Номер успешно добавлен абоненту: " + name);
        }

    }


    private static void phonePrintByNumber(Map<Long, String> map, String data, Scanner scan) {
        long number = Long.parseLong(data);
        if (map.containsKey(number)) {
            System.out.println("Имя абонента: " + map.get(number) + ". Номер телефона: +" + number);
        } else {
            System.out.println("Абонента с таким номером не существует. Введите имя:");
            String name = scan.nextLine();
            map.put(number, name);
            System.out.println("Номер +" + number + " присвоен абоненту: " + name);
        }
    }

    private static void printListSortByName(Map<Long, String> map) {
        TreeSet<String> sort = new TreeSet<>();
        for (Map.Entry<Long, String> pair : map.entrySet()) {
            sort.add(pair.getValue() + ". Номер телефона: +" + pair.getKey());
        }

        for (String name : sort) {
            System.out.println("Имя абонента: " + name);
        }
    }

    private static void command() {
        System.out.println("\nВведите имя или номер телефона.\nДля получения списка абонентов" +
                " введите команду \"LIST\".");
    }

}
