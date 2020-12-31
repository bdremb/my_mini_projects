import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CVSReader {

    //считает неправильно доходы и расходы
    public static final String CVS_LIST = "data/movementList.csv";
    public static final String REGEX_INCOME = "\\s+\\d{2}\\.\\d{2}\\.\\d{2}\\s\\d{2}\\.\\d{2}\\.\\d{2}\\s\\d+\\.\\d{2}";
    public static final String SEPARATE = "@%@";
    private static double summRub;
    private static ArrayList<String> linesCvs;
    private static Logger logger;
    public static final Marker ERRORS = MarkerManager.getMarker("ERROR");
    public static final Marker INFORMATION = MarkerManager.getMarker("INFO");

    public static void main(String[] args) {
        logger = LogManager.getRootLogger();
        linesCvs = Operations.getListCvs();
        System.out.println("Суммы расходов по организациям:");
        getExpensesSingleCompany(getCompaniesExpenses());
        getIncomeRuR();
        System.out.printf("Сумма расходов:\t = %.2f руб.%n", summRub);
    }

    private static ArrayList<String> getCompaniesExpenses() {
        ArrayList<String> result = new ArrayList<>();
        for (String line : linesCvs) {
            String[] res = Operations.getTrimLine(line).split("\\s");
            for (int i = 0; i < res.length; i++) {
                String companyAndCost = checkCurrencyAndAddToResult(res, i);
                if (companyAndCost != null) {
                    result.add(companyAndCost);
                }
            }
        }
        return result;
    }

    private static String checkCurrencyAndAddToResult(String[] res, int i) {
        String money = res[i];
        if (money.equals("RUR")) {
            String cost = res[i - 2].strip();
            logger.warn(INFORMATION, "checkCurrencyAndAddToResult()  Cost before matches - " + cost);
            return Operations.nameCompanyAndExpenses(cost, SEPARATE, res);
        } else if (money.equals("USD") || (money.equals("EUR"))) {
            String cost = res[i + 1].substring(res[i + 1].indexOf("\"") + 1, res[i + 1].lastIndexOf("\"")).replaceAll(",", ".");
            logger.warn(INFORMATION, "checkCurrencyAndAddToResult()  Cost before matches - " + cost);
            return Operations.nameCompanyAndExpenses(cost, SEPARATE, res);
        }
        return null;
    }

    private static void getIncomeRuR() {
        double income = 0;
        for (String line : linesCvs) {
            String[] res = Operations.getTrimLine(line).split("\\s{4}");
            for (String s : res) {
                logger.warn(INFORMATION, "getIncomeRuR() before matches income sum -  " + s);
                income += incomeMatches(s);
            }
        }
        System.out.printf("\nСумма доходов:\t = %.2f руб.%n", income);
    }

    private static double incomeMatches(String stringWithCost) {
        double income = 0;
        if (stringWithCost.matches(REGEX_INCOME)) {
            String cost = stringWithCost.substring(stringWithCost.lastIndexOf(" ") + 1).strip();
            try {
                income = Double.parseDouble(cost);
                logger.warn(INFORMATION, "getIncomeRuR() success parsinq cost =  " + cost);
            } catch (Exception e) {
                logger.warn(ERRORS, "getIncomeRuR() ошибка парсинга доходов : " + cost
                        + "\n ---" + Arrays.toString(e.getStackTrace()));
            }
        }
        return income;
    }

    private static void getExpensesSingleCompany(List<String> list) {
        for (String company : Operations.getSingleCompanies(list)) {
            double expenses = 0;
            String[] str;
            for (String string : list) {
                str = string.split(SEPARATE);
                if (str[0].equals(company) && str.length > 1) {
                    try {
                        expenses += Double.parseDouble(str[1]);
                    } catch (Exception e) {
                        logger.warn(ERRORS, "printExpenses(List<String> list) ошибка парсинга расхода компании: "
                                + company + "\n ---" + Arrays.toString(e.getStackTrace()));
                    }
                }
            }
            Operations.printExpensesCompanyAndSumm(company, expenses);
        }
    }

    public static double getSummRub() {
        return summRub;
    }

    public static void setSummRub(double summRub) {
        CVSReader.summRub = summRub;
    }

}
