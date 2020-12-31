import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Operations {
    public static final String REGEX_EXPENSES = "\\d+\\.\\d{2}";

    public static void printExpensesCompanyAndSumm(String company, double expenses) {
        if (!company.matches("\\s+") && expenses != 0) {
            CVSReader.setSummRub(CVSReader.getSummRub() + expenses);
            System.out.printf("%-30s = %.2f руб.\n", company, expenses);
        }
    }

    public static String nameCompanyAndExpenses(String cost, String separate, String[] res) {
        Logger logger = LogManager.getRootLogger();
        String result = "";
        if (cost.matches(REGEX_EXPENSES)) {
            logger.warn(CVSReader.INFORMATION, "checkCurrencyAndAddToResult()  If matches == 'true'  cost =  " + cost);
            String company = (res[0] + " " + res[1] + " " + res[2]).replaceAll("[\\\\/]", "");
            result = company + separate + cost + separate;
        }
        return result;
    }

    public static String getTrimLine(String line) {
        String string = line.replaceAll("[\\\\]", "/");
        return string.substring(string.indexOf("/") + 1).trim();
    }

    public static ArrayList<String> getListCvs() {
        ArrayList<String> list = null;
        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(CVSReader.CVS_LIST));
             BufferedReader br = new BufferedReader(inputStreamReader)
        ) {
            list = new ArrayList<>();
            br.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Set<String> getSingleCompanies(List<String> list) {
        Set<String> companies = new HashSet<>();
        for (String string : list) {
            String[] str = string.split(CVSReader.SEPARATE);
            companies.add(str[0]);
        }
        return companies;
    }

}
