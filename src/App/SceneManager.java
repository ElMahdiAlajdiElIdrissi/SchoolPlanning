package App;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class SceneManager {

    public static Scene getSchoolScheduleScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/SchoolSchedule.fxml"));
        return new Scene(root, 350, 250);
    }

    public static Scene getEnrolledCoursesScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/EnrolledCourses.fxml"));
        return new Scene(root, 500, 450);
    }

    public static Scene getTeacherAssignedCourses() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/AssignedCourses.fxml"));
        return new Scene(root, 500, 450);
    }
    public static Scene getEnlistedStudents() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/EnlistedStudents.fxml"));
        return new Scene(root, 500, 450);
    }

    public static Scene getRegisterCourses() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/RegisterCourses.fxml"));
        return new Scene(root, 490, 300);
    }

    public static Scene getLoginScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/Login.fxml"));
        return new Scene(root, 350, 150);
    }

    public static Scene getStartupScene() throws IOException{
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/Startup.fxml"));
        return new Scene(root, 350,150);
    }

    public static Scene getTeacherSchedule() throws IOException{
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/TeacherSchedule.fxml"));
        return new Scene(root, 400, 300);
    }

    public static Scene getRegisterScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("./views/RegisterUser.fxml"));

        return new Scene(root, 400, 250);
    }

    public static void alertError(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Something went wrong");
        alert.setContentText(text);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            ButtonType button = result.get();
        }
    }

    public static void alertVerify(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Everything went ok!");
        alert.setContentText(text);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            ButtonType button = result.get();
        }
    }
}
