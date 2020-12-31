import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SampleVariant {
    public static void main(String[] args) throws IOException {
        final String movementListFile = "data/movementList.csv";
        parse(movementListFile);
    }


    private static void parse(String movementListFile) throws IOException {
        List<String> records = Files.readAllLines(Paths.get(movementListFile));

        Pattern pattern = Pattern.compile("\"\\d+,\\d+\"");
        Map<String, Double[]> categories = new HashMap<>();

        for (int i = 1; i < records.size(); i++) {
            String line = records.get(i);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                String wrong = matcher.group();
                String good = wrong.replace(",", ".");
                line = line.replace(wrong, good);
            }

            String[] columns = line.split(",");
            String category = columns[5];
            category = category
                    .replaceAll("\\d+(\\++|\\.)\\d+(\\.\\d+)?", "")
                    .trim()
                    .replaceAll(" {3,}+.+", "");
            if (!categories.containsKey(category)) {
                categories.put(category, new Double[]{0.0, 0.0});
            }

            categories.get(category)[0] += parseDouble(columns[6]);
            categories.get(category)[1] += parseDouble(columns[7]);
           // System.out.println(columns[6]);

        }

        System.out.println("Общий приход " + sum(categories, 0));
        System.out.println("Общий расход " + sum(categories, 1));

        System.out.println("\nПо категориям:");
        for (String key : categories.keySet()) {
            System.out.println("Категория: " + key);
            System.out.println("Приход: " + categories.get(key)[0]);
            System.out.println("Расход: " + categories.get(key)[1]);
            System.out.println();
        }
    }

    private static Double parseDouble(String num) {
        return Double.parseDouble(num.replace("\"", ""));
    }

    private static Double sum(Map<String, Double[]> categories, int index) {
        return categories.values().stream().mapToDouble(e -> e[index]).sum();
    }
}
