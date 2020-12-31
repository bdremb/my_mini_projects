import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JDBCMain {
    private static String url = "jdbc:mysql://localhost:3306/skillbox?serverTimezone=Europe/Moscow&useSSL=false";
    private static String user = "root";
    private static String password = "testtest";
    private static String sqlCourseNameAndCount = "SELECT course_name, COUNT(*) AS count FROM Purchaselist GROUP BY course_name";
    private static String sqlCourseNameAndDate = "SELECT course_name FROM Purchaselist WHERE MONTH(subscription_date) = ";
    private static int decemberMonthCount = 12;
    private static Map<String, Integer> coursesAndCounts;

    public static void main(String[] args) {
        coursesAndCounts = new HashMap<>();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            getMapCourses(statement);
            printResult(statement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getMapCourses(Statement statement) {
        coursesAndCounts = new HashMap<>();
        try (ResultSet names = statement.executeQuery(sqlCourseNameAndCount)) {
            while (names.next()) {
                String nameCourse = names.getString("course_name");
                int countSubscription = names.getInt("count");
                coursesAndCounts.put(nameCourse, countSubscription);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printResult(Statement statement) {
        try {
            for (String string : coursesAndCounts.keySet()) {
                float rangeOfMonths = 0;
                float countSubscriptions = 0;
                int firstMonth = decemberMonthCount;
                for (int lastMonth = 1; lastMonth < decemberMonthCount; lastMonth++) {
                    statement.execute(sqlCourseNameAndDate + lastMonth);
                    ResultSet resultSet = statement.getResultSet();
                    while (resultSet.next()) {
                        if (resultSet.getString("course_name").equals(string)) {
                            countSubscriptions = coursesAndCounts.get(string);
                            firstMonth = Math.min(lastMonth, firstMonth);
                            rangeOfMonths = lastMonth + 1 - firstMonth;
                        }
                    }
                    resultSet.close();
                }
                System.out.printf(string + " = %.2f \n", countSubscriptions / rangeOfMonths);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getUrl() {
        return url;
    }

    public static void setUrl(String url) {
        JDBCMain.url = url;
    }

    public static String getUser() {
        return user;
    }

    public static void setUser(String user) {
        JDBCMain.user = user;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        JDBCMain.password = password;
    }

}
