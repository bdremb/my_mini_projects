package testsearch;

import java.util.ArrayList;
import java.util.TreeSet;

public class TreeTime {

    public static void find(String number, ArrayList<String> list) {

        TreeSet<String> set = new TreeSet<>(list);

        long start = System.nanoTime();

        if (set.contains(number)) {
            long timeFinding = System.nanoTime() - start;
            System.out.println("Поиск в TreeSet: номер " + number + " найден. Поиск занял " + timeFinding + "нс");
        } else {
            System.out.println("Поиск в TreeSet: номер " + number + " не найден.");
        }

    }
}
