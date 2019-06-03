import dao.Student;
import dao.StudentDao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class BackEndCode {
    private static int counter = 0;
    private static DataBase db = new DataBase();

    public static void main(String[] args) throws SQLException{
        databaseConnection("databasescript.txt");

        StudentDao studentDao = new StudentDao( db.getURL(), db.getUSERNAME(), db.getPASSWORD());
        Student student = studentDao.getStudentByID(1);

        System.out.println(student.getFirstName() + " " + student.getLastName());
    }

    public static void databaseConnection(String textFile){
        /*final String URL = "jdbc:mysql://localhost/SchoolPlanner?serverTimezone=UTC";
        final String USERNAME = "root";
        final String PASSWORD = "root";*/
        final String SCRIPT = databaseStartUp(textFile);

        String[] queries = SCRIPT.split(";");
        for (int i = 0; i < queries.length; i++) {
            //System.out.println(queries[i].concat(";"));
            int firstIndex = SCRIPT.indexOf(queries[i].concat(";"));
            int lastIndex = queries[i].length() + firstIndex;
            try (
                    Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
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
