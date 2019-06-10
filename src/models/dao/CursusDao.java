package models.dao;

import models.entities.Cursus;

import java.sql.*;

public class CursusDao {
    private String url;
    private String user;
    private String password;

    public CursusDao(){

    }

    public CursusDao(String url, String user, String password){
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

    public Cursus getCursusByID(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM cursus WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Cursus cursus = new Cursus();
                    cursus.setId(id);
                    cursus.setNaamCursus(result.getString("Naam_Cursus"));
                    cursus.setEindDatum(result.getString("Eind_Datum"));
                    cursus.setStartDatum(result.getString("Start_Datum"));
                    cursus.setDepartementID(result.getInt("Departement_Id"));
                    return cursus;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void updateCursus(Cursus cursus) throws SQLException{
        try (Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE Cursus SET Naam_Cursus=?, Start_Datum=?, Eind_Datum =?, Departement_Id=? WHERE Id=? ")){
            statement.setString(1, cursus.getNaamCursus());
            statement.setString(2, cursus.getStartDatum());
            statement.setString(3, cursus.getEindDatum());
            statement.setInt(4, cursus.getDepartementID());
            statement.setInt(5, cursus.getId());
            statement.executeUpdate();
        } catch (SQLException se){
            throw new SQLException("Something went wrong");
        }
    }

    public void insertNewCursus(String cursusnaam, String startdatum, String einddatum, int departementID) throws SQLException{
        try (Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO Cursus (Naam_Cursus, Start_Datum, Eind_Datum, Departement_Id)" +
                " VALUES  + (" + cursusnaam + ", " + startdatum + ", " + einddatum + ", " + departementID + "),")){
            statement.execute();
        } catch (SQLException se){
            throw new SQLException("You tried to create a new cursus and failed");
        }
    }


    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
