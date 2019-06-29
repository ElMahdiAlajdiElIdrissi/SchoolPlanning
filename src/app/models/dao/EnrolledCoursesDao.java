package app.models.dao;

import app.models.entities.Course;
import app.models.entities.EnrolledCourse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data-Access-Objects class created for EnrolledCourses
 */
public class EnrolledCoursesDao {
    private String url;
    private String user;
    private String password;

    public EnrolledCoursesDao() {

    }

    public EnrolledCoursesDao(String url, String user, String password) {
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
     * public class for fetching all the enrolledcourses with all their info
     * @param id
     * @return
     * @throws SQLException
     */
    public EnrolledCourse getEnrolledCourses(int id) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM enrolledcourses WHERE Course_Id = ?")) {
            statement.setInt(1, id);
            try (ResultSet result = statement.executeQuery()) {
                if (result.next()) {
                    EnrolledCourse course = new EnrolledCourse();
                    course.setCourseId(id);
                    course.setStudentId(result.getInt("Student_Id"));
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
     * public method for updating an already existing EnrolledCourse
     * @param Course
     * @throws SQLException
     */
    public void updateEnrolledCourse(EnrolledCourse Course) throws SQLException {
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("UPDATE enrolledcourses SET Student_Id=? WHERE Course_Id=? ")) {
            statement.setInt(1, Course.getStudentId());
            statement.setInt(2, Course.getCourseId());
            statement.executeUpdate();
        } catch (SQLException se) {
            throw new SQLException("Something went wrong");
        }
    }

    /**
     * public method for fetching all the enrolledcourses for a student
     * @param studentId
     * @return
     */
    public List<EnrolledCourse> getAllEnrolledCourses(int studentId){
        List<EnrolledCourse> s = new ArrayList<>();
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement("select * from enrolledcourses where Student_Id = ?;")){
            stm.setInt(1,studentId);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                EnrolledCourse ic = new EnrolledCourse();
                ic.setCourseId(rs.getInt("Course_Id"));
                ic.setStudentId(rs.getInt("Student_Id"));
                s.add(ic);
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return s;
    }

    /**
     * public method for fetching all existing courses a student's NOT enrolled in
     * @param id
     * @return
     */
    public List<Course> getAllNotEnrolledCourses(int id){
        List<Course> s = new ArrayList<>();
        try(Connection conn = getConnection();
            PreparedStatement stm = conn.prepareStatement("select Id, Course_Name from course where Id <> all(select Course_Id from enrolledcourses where enrolledcourses.Student_Id = ?);")){

            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                Course c = new Course();
                c.setId(rs.getInt(1));
                c.setCourseName(rs.getString(2));
                s.add(c);
            }

        }catch(SQLException ex){
            ex.printStackTrace();
        }
        return s;
    }

    /**
     * public method for linking a course to a student
     * @param studentID
     * @throws SQLException
     */
    public void insertNewEnrolledCourse(int studentID) throws SQLException{
        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT IGNORE INTO course (Student_Id)" +
                     " VALUES  + (" + studentID + "),")){
            statement.execute();
        } catch (SQLException se){
            throw new SQLException("You tried to create a new 'ingeschreven cursus' and failed");
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

