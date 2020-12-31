package testsearch;

import java.util.ArrayList;
import java.util.Collections;

public class BinaryTime {

    public static void find(String number, ArrayList<String> list) {

        long start = System.nanoTime();

        if (Collections.binarySearch(list, number) >= 0) {
            long timeFinding = System.nanoTime() - start;
            System.out.println("Бинарный поиск: номер " + number + " найден. Поиск занял " + timeFinding + "нс");
        } else {
            System.out.println("Бинарный поиск: номер " + number + " не найден.");
        }

    }
}
