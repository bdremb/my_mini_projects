package testsearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class NumberFinder {

    public static final int FIRST_REGION = 1;
    public static final int LAST_REGION = 199;

    public static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] letters = {"A", "B", "C", "E", "H", "K",
                "M", "O", "P", "T", "X"};

        generateNumbersList(list, letters);

        Collections.sort(list);

        System.out.println("Количество номеров в базе данных = " + list.size());

        for (; ; ) {
            System.out.println("Введите номер для поиска:");

            String number = scanner.nextLine().strip().toUpperCase();

            ContainsTime.find(number, list); //поиск перебором

            BinaryTime.find(number, list);  //бинарный поиск

            HashTime.find(number, list);    //поиск по HashSet

            TreeTime.find(number, list);    //поиск по TreeSet
        }
    }


    private static void generateNumbersList(ArrayList<String> array, String[] lit) {

        for (String startLetter : lit) {

            for (int num = 111; num <= 999; num = num + 111) {

                for (String firstLetter : lit) {

                    for (String secondLetter : lit) {

                        for (int region = FIRST_REGION; region <= LAST_REGION; region++) {
                            String reg = "" + region;
                            if (region < 10) {
                                reg = "" + 0 + region;
                            }
                            array.add(startLetter + num + firstLetter + secondLetter + reg);
                        }
                    }
                }
            }
        }
    }

}


