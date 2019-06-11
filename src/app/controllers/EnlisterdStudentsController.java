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
 * public class which controls our EnlisterdStudentsFXML
 */
public class EnlisterdStudentsController implements Initializable {

    @FXML
    private ListView table;

    private StringBuilder enlistedStudents = new StringBuilder();

    /**
     * public method used for fetching enlisted student's names
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBase db = new DataBase();
        try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
            PreparedStatement stm = conn.prepareStatement("select First_Name from student where Id = any(select Student_Id from enrolledcourses where Course_Id = any(select Course_Id from assignedcourses where Teacher_Id = ?));")){
            stm.setInt(1, GlobalVars.getDocentId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                enlistedStudents.append(rs.getString(1) + "\n");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        ObservableList<String> teacherAndCourse = FXCollections.observableArrayList();
        teacherAndCourse.add("These are the students already enlisted to your course: ");
        teacherAndCourse.add(enlistedStudents.toString());
        table.setItems(teacherAndCourse);
        table.setPrefSize(400,400);
    }

    @FXML
    private Button back;

    /**
     * private method for the back button which will bring us back to the TeachersSchedule
     * @param ev
     * @throws IOException
     */
    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getTeacherSchedule());
    }
}
