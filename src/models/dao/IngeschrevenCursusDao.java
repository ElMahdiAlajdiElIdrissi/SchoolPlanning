package models.dao;

import models.entities.Cursus;
import models.entities.IngeschrevenCursus;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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

    public List<IngeschrevenCursus> getAllIngeschrevenCursussen(int studentId){
        List<IngeschrevenCursus> s = new ArrayList<>();
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from ingeschrevencursussen where Student_Id = ?;")){
            stm.setInt(1,studentId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                IngeschrevenCursus ic = new IngeschrevenCursus();
                ic.setCursusID(rs.getInt("Cursus_Id"));
                ic.setStudentID(rs.getInt("Student_Id"));
                s.add(ic);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return s;
    }

    public List<Cursus> getAllNietIngeschrevenCursussen(int id){
        List<Cursus> s = new ArrayList<>();
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement("select Id, Naam_Cursus from cursus where Id <> all(select Cursus_Id from ingeschrevencursussen where ingeschrevencursussen.Student_Id = ?);")){

            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Cursus c = new Cursus();
                c.setId(rs.getInt(1));
                c.setNaamCursus(rs.getString(2));
                s.add(c);
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return s;
    }

    public void insertNewIngeschrevenCursus(int studentID) throws SQLException{
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO Cursus (Student_Id)" +
                     " VALUES  + (" + studentID + "),")){
            statement.execute();
        } catch (SQLException se){
            throw new SQLException("You tried to create a new 'ingeschreven cursus' and failed");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

