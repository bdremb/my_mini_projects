import java.util.Arrays;

public class ColorWord {
    public static void main(String[] args) {
        String color = "Каждый охотник желает знать, где сидит фазан";
        String[] colorWord = color.split(",?\\s+");

        for (int i = colorWord.length - 1; i > colorWord.length / 2; i--) {

            int k = colorWord.length - i - 1;  // 7 - 6 - 1 = 0; k = 0;

            String temp = colorWord[k];  // записываем в память 0-й элемент из 6-ми

            colorWord[k] = colorWord[i];  // затем в 0-й элемент записываем 6-й

            colorWord[i] = temp;  // затем в 6-й элемент записываем 0-й

            //на 2-й итерации меняются местами 5-й и 1-й элементы
            //на 3-й итерации меняются местами 4-й и 2-й элементы (всего 3 итерации 7/2  = 3)

            System.out.println("i= " + i + "  k=  " + k + "  " + Arrays.toString(colorWord));

        }
        System.out.println("======================================");
        System.out.println(Arrays.toString(colorWord));
    }
}
