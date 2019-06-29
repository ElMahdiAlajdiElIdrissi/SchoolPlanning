package app.models.dao;

import app.models.entities.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data-Access-Objects class created for Student
 */
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

    /**
     * public method for fetching a student by his ID
     * @param id
     * @return
     * @throws SQLException
     */
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
                    student.setUserName(result.getString("User_Name"));
                    student.setPassword(result.getString("Password"));
                    return student;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    /**
     * public method for changing a student's personal data
     * @param student
     * @throws SQLException
     */
    public void updateStudent(Student student) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE student SET First_Name=?, Last_Name=?, User_Name=?, Password=? WHERE Id=? ")) {
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getUserName());
            statement.setString(4, student.getPassword());
            statement.setInt(5, student.getId());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    /**
     * public method for returning all registered students
     * @return
     */
    public List<Student> getAllStudents(){
        List<Student> s = new ArrayList<>();
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from Student;")){
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Student stu = new Student();
                stu.setFirstName(rs.getString("First_Name"));
                stu.setLastName(rs.getString("Last_Name"));
                stu.setUserName(rs.getString("User_Name"));
                stu.setPassword(rs.getString("Password"));
                s.add(stu);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return s;
    }

    /**
     * public method for registering a new student
     * @param firstName
     * @param lastName
     * @param userName
     * @param password
     * @throws SQLException
     */
    public void insertNewStudent(String firstName, String lastName, String userName, String password) throws SQLException{
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO course (First_Name, Last_Name, User_Name, Password)" +
                     " VALUES  + (" + firstName + ", " + lastName + ", " + userName + ", " + password + "),")){
            statement.execute();
        } catch (SQLException se){
            throw new SQLException("You tried to create a new student and failed");
        }
    }

    /**
     * private method for easy connection setup
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

