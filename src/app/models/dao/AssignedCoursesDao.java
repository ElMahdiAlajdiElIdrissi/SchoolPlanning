package app.models.dao;

import app.models.entities.AssignedCourse;
import java.sql.*;

/**
 * Data-Access-Objects class created for AssignedCourses
 */
public class AssignedCoursesDao {
    private String url;
    private String user;
    private String password;

    public AssignedCoursesDao() {

    }

    public AssignedCoursesDao(String url, String user, String password) {
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
     * public method for fetching all the courses a teacher teaches
     * @param id
     * @return
     * @throws SQLException
     */
    public AssignedCourse getAssignedCourseById(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM assignedcourses WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    AssignedCourse course = new AssignedCourse();
                    course.setCourseId(id);
                    course.setTeacherId(result.getInt("Teacher_Id"));
                    return course;
                } else {
                    return null;
                }
            }
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    /**
     * public method for updating a teacher's assigned course(s)
     * @param course
     * @throws SQLException
     */
    public void updateAssignedCourse(AssignedCourse course) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE assignedcourses SET Teacher_Id WHERE Course_Id=? ")) {
            statement.setInt(1, course.getTeacherId());
            statement.setInt(2, course.getCourseId());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    /**
     * public method for assigning a new course to a teacher UNUSED! INCORRECT STATEMENT!
     * @param teacherId
     * @throws SQLException
     */
    /*public void insertNewAssignedCourse(int teacherId) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO course (Teacher_Id)" +
                     " VALUES  + (" + teacherId + "),")) {
            statement.execute();
        } catch (SQLException se) {
            throw new SQLException("You tried to create a new 'assigned course' and failed");
        }
    }*/

    /**
     * private method for easy connection setup
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}

