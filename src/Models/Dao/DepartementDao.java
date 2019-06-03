package Models.Dao;

import Models.Entities.Departement;

import java.sql.*;

public class DepartementDao {
    private String url;
    private String user;
    private String password;

    public DepartementDao() {

    }

    public DepartementDao(String url, String user, String password) {
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

    public Departement getDepartementByID(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM departement WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Departement departement = new Departement();
                    departement.setId(id);
                    departement.setDepartementNaam(result.getString("Departement_Naam"));
                    return departement;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void updateDepartement(Departement departement) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE departement SET Departement_Naam=? WHERE Id=? ")) {
            statement.setString(1, departement.getDepartementNaam());
            statement.setInt(2, departement.getId());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
