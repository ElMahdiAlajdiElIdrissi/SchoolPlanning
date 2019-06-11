package App.models.dao;

import App.models.entities.Teacher;

import java.sql.*;

public class TeacherDao {
    private String url;
    private String user;
    private String password;

    public TeacherDao() {

    }

    public TeacherDao(String url, String user, String password) {
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

    public Teacher getTeacherById(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM teacher WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Teacher teacher = new Teacher();
                    teacher.setId(id);
                    teacher.setFirstName(result.getString("First_Name"));
                    teacher.setLastName(result.getString("Last_Name"));
                    teacher.setDepartmentID(result.getInt("Department_Id"));
                    return teacher;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void updateTeacher(Teacher teacher) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE teacher SET First_Name=?, Last_Name=?, Department_Id=? WHERE Id=? ")) {
            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setInt(3, teacher.getDepartmentID());
            statement.setInt(4, teacher.getId());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    public void insertNewTeacher(String firstName, String lastName, int id, String userName, String password) throws SQLException{
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO course (First_Name, Last_Name, Department_Id, User_Name, Password)" +
                     " VALUES  + (" + firstName + ", " + lastName + ", " + id + ", " + userName + ", " + password + "),")){
            statement.execute();
        } catch (SQLException se){
            throw new SQLException("You tried to create a new teacher and failed");
        }
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
