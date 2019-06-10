import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DocentPlanningController {
    @FXML
    private Button assignedCourse;

    @FXML
    private void assignedCourses(ActionEvent ev) throws IOException {
        ((Stage) assignedCourse.getScene().getWindow()).setScene(SceneManager.getDocentAssignedCourses());
    }

    @FXML
    private Button enlistedStudent;

    @FXML
    private void enlistedStudents(ActionEvent ev) throws IOException {
        ((Stage) enlistedStudent.getScene().getWindow()).setScene(SceneManager.getEnlistedStudents());
    }

    @FXML
    private Button logout;

    @FXML
    private void logout(ActionEvent ev) throws IOException {
        ((Stage) logout.getScene().getWindow()).setScene(SceneManager.getStartupScene());
    }
}
