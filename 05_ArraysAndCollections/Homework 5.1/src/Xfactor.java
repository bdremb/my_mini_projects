import java.util.Arrays;
import java.util.Scanner;

public class Xfactor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();

        String [][] matrix = new String[n][n];

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n ; j++) {

                if (i == j || i == n - 1 - j) {
                    matrix[i][j] = i +""+ j;

                } else {
                    matrix[i][j] = ".:";
                }

            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }





    }
}
