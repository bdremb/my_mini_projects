import java.sql.*;

public class DBConnection {
    private static Connection connection;
    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "testtest";
    private static StringBuilder insertQuery = new StringBuilder();

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?serverTimezone=Europe/Moscow&user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count2");
                connection.createStatement().execute("CREATE TABLE voter_count2(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "PRIMARY KEY(id))");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsert() throws SQLException {
        String sql = "INSERT INTO voter_count2(name, birthDate) " +
                "VALUES" + insertQuery;
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String voter) throws SQLException {
        insertQuery.append((insertQuery.length() == 0 ? "" : ",") +
                "('" + voter + "')");
    }

    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate FROM voter_count2 GROUP BY name HAVING COUNT(id) > 1";
        //String sql = "SELECT name, count(id) FROM voter_count GROUP BY name; ";  // 2-й вариант

        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name"));
        }
        rs.close();
    }

    public static StringBuilder getInsertQuery() {
        return insertQuery;
    }

    public static void setInsertQuery(StringBuilder insertQuery) {
        DBConnection.insertQuery = insertQuery;
    }
}