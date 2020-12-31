package testsearch;

import java.util.ArrayList;

public class ContainsTime {

    public static void find(String number, ArrayList<String> list) {

        long start = System.nanoTime();

        if (list.contains(number)) {
            long timeFinding = System.nanoTime() - start;
            System.out.println("Поиск перебором: номер " + number + " найден. Поиск занял " + timeFinding + "нс");
        } else {
            System.out.println("Поиск перебором: номер " + number + " не найден.");
        }

    }
}
