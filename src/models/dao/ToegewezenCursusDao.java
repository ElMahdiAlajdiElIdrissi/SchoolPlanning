package models.dao;

import models.entities.ToegewezenCursus;

import java.sql.*;

public class ToegewezenCursusDao {
    private String url;
    private String user;
    private String password;

    public ToegewezenCursusDao() {

    }

    public ToegewezenCursusDao(String url, String user, String password) {
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

    public ToegewezenCursus getToegewezenCursusByID(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM toegewezencursus WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    ToegewezenCursus cursus = new ToegewezenCursus();
                    cursus.setCursusID(id);
                    cursus.setDocentID(result.getInt("Docent_Id"));
                    return cursus;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void updateToegewezenCursus(ToegewezenCursus cursus) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE toegewezencursus SET Docent_Id WHERE Cursus_Id=? ")) {
            statement.setInt(1, cursus.getDocentID());
            statement.setInt(2, cursus.getCursusID());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void insertNewStudent(int docentID) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO Cursus (Docent_Id)" +
                     " VALUES  + (" + docentID + "),")) {
            statement.execute();
        } catch (SQLException se) {
            throw new SQLException("You tried to create a new 'toegewezen cursus' and failed");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

