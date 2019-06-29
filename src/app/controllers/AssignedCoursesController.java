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

/**
 * public class which controls our AssignedCoursesFXML
 */
public class AssignedCoursesController implements Initializable {

    @FXML
    private Button back;

    /**
     * private method for the back button which will bring us back to the teachersSchedule
     * @param ev the necessary ActionEvent
     * @throws IOException could throw an Input-Output Exception
     */
    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getTeacherSchedule());
    }

    @FXML
    private ListView table;

    private StringBuilder assignedCourses = new StringBuilder();

    /**
     * public method to esthablish connection to our database in order to fetch assigned courses by ID and display them immediately
     * @param location
     * @param resources
     */
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
