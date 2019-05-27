package eindwerk_schoolplanner;

import java.sql.*;
import java.util.Scanner;

public class BackEndCode {
    private static int counter = 0;
    public static void main(String[] args) throws SQLException {
        final String URL = "jdbc:mysql://noelvaes.eu/StudentDB?serverTimezone=UTC";//"jdbc:mysql://localhost/SchoolPlanner?serverTimezone=UTC";
        final String USERNAME = "student";//"root";
        final String PASSWORD = "student123";//"Desrath1990";
        final String sortOnWhat = dataInput();

        try (
                Connection conn = DriverManager.getConnection(URL,
                        USERNAME, PASSWORD);
                Statement statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                ResultSet result = statement.executeQuery(sortData(sortOnWhat))
        ) {

            while (result.next()) {
                System.out.println(result.getString(sortOnWhat));
            }
        } catch (
                SQLException se) {
            se.printStackTrace();
        }
    }

    private static String sortData(String sortOnWhat) {
        String giveBack = "SELECT ".concat(sortOnWhat).concat(" from Beers order by ").concat(sortOnWhat);
        if (counter < 2) {
            switch (counter) {
                case 0:
                    giveBack.concat(" asc");
                    counter++;
                    break;
                case 1:
                    giveBack.concat(" desc");
                    counter++;
                    break;
            }
            } else {
            counter = 0;
        }
        return giveBack;
    }

    private static String dataInput(){
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
