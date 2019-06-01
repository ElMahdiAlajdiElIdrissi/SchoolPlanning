package dao;

import java.sql.*;

public class IngeschrevenCursusDao {
    private String url;
    private String user;
    private String password;

    public IngeschrevenCursusDao() {

    }

    public IngeschrevenCursusDao(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public IngeschrevenCursus getIngeschrevenCursusByID(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM ingeschrevencursussen WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    IngeschrevenCursus cursus = new IngeschrevenCursus();
                    cursus.setCursusID(id);
                    cursus.setStudentID(result.getInt("Student_Id"));
                    return cursus;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void updateIngeschrevenCursus(IngeschrevenCursus cursus) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE ingeschrevencursussen SET Student_Id=? WHERE Cursus_Id=? ")) {
            statement.setInt(1, cursus.getStudentID());
            statement.setInt(2, cursus.getCursusID());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

