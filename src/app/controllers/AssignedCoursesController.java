package app.controllers;

import app.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AssignedCoursesController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getTeacherSchedule());
    }

    @FXML
    private ListView table;

    private StringBuilder assignedCourses = new StringBuilder();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBase db = new DataBase();
        try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
            PreparedStatement stm = conn.prepareStatement("SELECT course.Course_Name from assignedcourses join course on assignedcourses.Course_Id = course.Id where Teacher_Id = ?")){
            stm.setInt(1, GlobalVars.getDocentId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                assignedCourses.append(rs.getString(1) + "\n");
//                System.out.println(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        ObservableList<String> assignedCoursesList = FXCollections.observableArrayList();
        assignedCoursesList.add("These are the courses you are already assigned to: ");
        assignedCoursesList.add(assignedCourses.toString());
        table.setItems(assignedCoursesList);
        table.setPrefSize(400,400);
    }

}
