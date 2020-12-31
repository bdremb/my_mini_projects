package testsearch;

import java.util.ArrayList;
import java.util.HashSet;

public class HashTime {

    public static void find(String number, ArrayList<String> list) {

        HashSet<String> set = new HashSet<>(list);

        long start = System.nanoTime();

        if (set.contains(number)) {
            long timeFinding = System.nanoTime() - start;
            System.out.println("Поиск в HashSet: номер " + number + " найден. Поиск занял " + timeFinding + "нс");
        } else {
            System.out.println("Поиск в HashSet: номер " + number + " не найден.");
        }

    }
}
