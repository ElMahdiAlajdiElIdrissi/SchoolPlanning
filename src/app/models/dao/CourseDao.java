package app.models.dao;

import app.models.entities.Course;
import java.sql.*;

/**
 * Data-Access-Objects class created for Course
 */
public class CourseDao {
    private String url;
    private String user;
    private String password;

    public CourseDao(){

    }

    public CourseDao(String url, String user, String password){
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
     * public method for fetching all the courses with their info
     * @param id
     * @return
     * @throws SQLException
     */
    public Course getCourseById(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM course WHERE Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    Course course = new Course();
                    course.setId(id);
                    course.setCourseName(result.getString("Course_Name"));
                    course.setEndDate(result.getString("End_Date"));
                    course.setStartDate(result.getString("Start_Date"));
                    course.setDepartmentID(result.getInt("Department_Id"));
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
     * public method for updating an already existing course
     * @param course
     * @throws SQLException
     */
    public void updateCourse(Course course) throws SQLException{
        try (Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("UPDATE course SET Course_Name=?, Start_Date=?, End_Date =?, Department_Id=? WHERE Id=? ")){
            statement.setString(1, course.getCourseName());
            statement.setString(2, course.getStartDate());
            statement.setString(3, course.getEndDate());
            statement.setInt(4, course.getDepartmentId());
            statement.setInt(5, course.getId());
            statement.executeUpdate();
        } catch (SQLException se){
            throw new SQLException("Something went wrong");
        }
    }

    /**
     * public method for creating a new course
     * @param courseName
     * @param startDate
     * @param endDate
     * @param departmentId
     * @throws SQLException
     */
    //TODO 4 parameters is te veel, een instantie van Course kan hier dienen als parameter
    public void insertNewCourse(String courseName, String startDate, String endDate, int departmentId) throws SQLException{
        try (Connection conn = getConnection();
        PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO course (Course_Name, Start_Date, End_Date, Department_Id)" +
                " VALUES  + (" + courseName + ", " + startDate + ", " + endDate + ", " + departmentId + "),")){
            statement.execute();
        } catch (SQLException se){
            throw new SQLException("You tried to create a new cursus and failed");
        }
    }

    /**
     * private method for easy connection setup
     * @return
     * @throws SQLException
     */
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection(url, user, password);
    }
}
