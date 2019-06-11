package App.controllers;

import App.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import App.models.dao.EnrolledCoursesDao;
import App.models.entities.Course;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterCourseController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolScheduleScene());
    }

    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBase db = new DataBase();

        ObservableList<String> choices = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
             PreparedStatement statement = conn.prepareStatement("select * from course where Id <> all(select Course_Id from enrolledcourses where enrolledcourses.Student_Id = ?);")) {
            statement.setInt(1, GlobalVars.getStudentId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String name = rs.getString("Course_Name");
                choices.add(name);
            }
            choiceBox.setItems(choices);
            choiceBox.getSelectionModel().selectFirst();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @FXML
    private TextField UserName;

    @FXML
    private TextField Password;

    @FXML
    private void registerCourse(ActionEvent ev) throws IOException{
        DataBase db = new DataBase();
        EnrolledCoursesDao ecdao = new EnrolledCoursesDao(db.getURL(), db.getUSERNAME(), db.getPASSWORD());

        List<Course> a = ecdao.getAllNotEnrolledCourses(GlobalVars.getStudentId());
        String course = choiceBox.getSelectionModel().getSelectedItem();
        String un = UserName.getText().trim();
        String pw = Password.getText().trim();

        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).getCourseName().equals(course)){
                GlobalVars.setCourseId(a.get(i).getId());
            }
        }

        String sql = "select Id, User_Name, Password from Student where Id=? and User_Name=? and Password=?";
        String sql2 = "insert ignore into enrolledcourses(Student_Id, Course_Id) VALUES (?, ?)";

        if(course != null && un != null && pw != null ){
            try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2)){
                stmt.setInt(1, GlobalVars.getStudentId());
                stmt.setString(2,un);
                stmt.setString(3,pw);

                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        stmt2.setInt(1, GlobalVars.getStudentId());
                        stmt2.setInt(2, GlobalVars.getCourseId());
                        stmt2.executeUpdate();
                        SceneManager.alertVerify("Successfully registered for the course");
                        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolScheduleScene());
                    }else{
                        SceneManager.alertError("Credentials do not match the logged in user");
                    }
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            SceneManager.alertError("Error registering in for course...");
        }
    }
}
