package app.controllers;

import app.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EnrolledCoursesController implements Initializable {


    @FXML
    private TextField searchField;

    @FXML
    private ListView table;

    @FXML
    private void searchTeacherOrStudent(ActionEvent event)throws IOException {
        DataBase db = new DataBase();
        String search = searchField.getText().trim();
        String queryDocent = "SELECT DISTINCT First_Name, Last_Name, Course_Name, Start_Date, End_Date FROM teacher " +
                "join assignedcourses on teacher.Id = assignedcourses.Teacher_Id " +
                "join course on assignedcourses.Course_Id = course.Id " +
                "WHERE First_Name=? OR Last_Name=? OR Course_Name=?;";

        try (Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
             PreparedStatement st = conn.prepareStatement(queryDocent)) {
            st.setString(1, search);
            st.setString(2, search);
            st.setString(3, search);
            ResultSet rs = st.executeQuery();

            ObservableList<String> teacherAndCourse = FXCollections.observableArrayList();
            while(rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    teacherAndCourse.add(rs.getString(i) + " ");
                }
            }
            if (teacherAndCourse.isEmpty()) {
                SceneManager.alertError("The teacher/course you were looking for doesn't exist");
                teacherAndCourse.add("These are the courses you already enlisted in: ");
                teacherAndCourse.add(enlistedCourses.toString());
            }
            table.setItems(teacherAndCourse);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private Button back;

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolScheduleScene());
    }

    private StringBuilder enlistedCourses = new StringBuilder();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBase db = new DataBase();
        try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
            PreparedStatement stm = conn.prepareStatement("SELECT course.Course_Name from enrolledcourses join course on enrolledcourses.Course_Id = course.Id where Student_Id = ?;")){
            stm.setInt(1, GlobalVars.getStudentId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                enlistedCourses.append(rs.getString(1) + "\n");
//                System.out.println(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
