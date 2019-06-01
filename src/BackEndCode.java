import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class BackEndCode {
    private static int counter = 0;

    public static void main(String[] args) throws SQLException{
        databaseConnection("databasescript.txt");
    }

    public static void databaseConnection(String textFile){
        final String URL = "jdbc:mysql://localhost/SchoolPlanner?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "Desrath1990";
        final String SCRIPT = databaseStartUp(textFile);

        String[] queries = SCRIPT.split(";");
        for (int i = 0; i < queries.length; i++) {
            //System.out.println(queries[i].concat(";"));
            int firstIndex = SCRIPT.indexOf(queries[i].concat(";"));
            int lastIndex = queries[i].length() + firstIndex;
            try (
                    Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                    PreparedStatement statement1 = conn.prepareStatement(SCRIPT.substring(firstIndex, lastIndex).concat(";"));
            ) {
                statement1.execute();

            } catch (SQLException se) {
                se.printStackTrace();
            }
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

    private static String databaseStartUp(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            List<String> lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
            for (String line : lines) {
                    sb.append(line);
            }
        } catch (IOException e) {
            System.out.println("Something went wrong while reading your text-file!");
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }

    private static String dataInput(){
        System.out.println("Please type the columnname of what you'd like sorted");
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
