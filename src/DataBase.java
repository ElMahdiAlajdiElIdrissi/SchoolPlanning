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
}
