package models.dao;

import models.entities.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private String url;
    private String user;
    private String password;

    public StudentDao() {

    }

    public StudentDao(String url, String user, String password) {
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

    public Student getStudentByID(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM student WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Student student = new Student();
                    student.setId(id);
                    student.setFirstName(result.getString("First_Name"));
                    student.setLastName(result.getString("Last_Name"));
                    student.setGebruikersNaam(result.getString("Gebruikers_Naam"));
                    student.setPassword(result.getString("Passwoord"));
                    return student;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void updateStudent(Student student) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE student SET First_Name=?, Last_Name=?, Gebruikers_Naam=?, Passwoord=? WHERE Id=? ")) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getGebruikersNaam());
            statement.setString(4, student.getPassword());
            statement.setInt(5, student.getId());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public List<Student> getAllStudents(){
        List<Student> s = new ArrayList<>();
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from Student;")){
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student stu = new Student();
                stu.setFirstName(rs.getString("first_name"));
                stu.setLastName(rs.getString("last_name"));
                stu.setGebruikersNaam(rs.getString("gebruikers_naam"));
                stu.setPassword(rs.getString("passwoord"));
                s.add(stu);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return s;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
