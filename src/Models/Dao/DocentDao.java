package Models.Dao;

import Models.Entities.Docent;

import java.sql.*;

public class DocentDao {
    private String url;
    private String user;
    private String password;

    public DocentDao() {

    }

    public DocentDao(String url, String user, String password) {
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

    public Docent getDocentByID(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM docent WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Docent docent = new Docent();
                    docent.setId(id);
                    docent.setVoorNaam(result.getString("Voor_Naam"));
                    docent.setAchterNaam(result.getString("Achter_Naam"));
                    docent.setDepartementID(result.getInt("Departement_Id"));
                    return docent;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void updateDocent(Docent docent) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE docent SET Voor_Naam=?, Achter_Naam=?, Departement_Id=? WHERE Id=? ")) {
            statement.setString(1, docent.getVoorNaam());
            statement.setString(2, docent.getAchterNaam());
            statement.setInt(3,docent.getDepartementID());
            statement.setInt(4, docent.getId());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
