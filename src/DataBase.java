import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DataBase {
    final String URL = "jdbc:mysql://localhost/SchoolPlanner?serverTimezone=UTC";
    final String USERNAME = "root";
    final String PASSWORD = "root";

    public DataBase() {
    }

    public String getURL() {
        return URL;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public String getPASSWORD() {
        return PASSWORD;
    }

    private String readScript(String fileName){
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

    public void executeScript(String textFile){
        final String SCRIPT = readScript(textFile);

        String[] queries = SCRIPT.split(";");
        for (int i = 0; i < queries.length; i++) {
            int firstIndex = SCRIPT.indexOf(queries[i].concat(";"));
            int lastIndex = queries[i].length() + firstIndex;
            try (
                    Connection conn = DriverManager.getConnection(getURL(), getUSERNAME(), getPASSWORD());
                    PreparedStatement statement1 = conn.prepareStatement(SCRIPT.substring(firstIndex, lastIndex).concat(";"));
            ) {
                statement1.execute();

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
