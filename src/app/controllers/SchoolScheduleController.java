package app.controllers;

import app.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * public class which controls our SchoolScheduleFXML
 */
public class SchoolScheduleController {

    @FXML
    private Button enrolledCourses;

    /**
     * private method for progressing to our EnrolledCoursesScene
     * @param ev
     * @throws IOException
     */
    @FXML
    private void clickedOnEnrolledCourses(ActionEvent ev) throws IOException {
        ((Stage) enrolledCourses.getScene().getWindow()).setScene(SceneManager.getEnrolledCoursesScene());
    }

    @FXML
    private Button registerCourses;

    /**
     * private method for progressing to our RegisterCoursesScene
     * @param ev
     * @throws IOException
     */
    @FXML
    private void clickedOnRegisterCourses(ActionEvent ev) throws IOException {
        ((Stage) registerCourses.getScene().getWindow()).setScene(SceneManager.getRegisterCourses());
    }

    @FXML
    private Button logout;

    /**
     * private method for the logout button which will bring us back to the StartupScene
     * @param ev
     * @throws IOException
     */
    @FXML
    private void logout(ActionEvent ev) throws IOException {
        ((Stage) logout.getScene().getWindow()).setScene(SceneManager.getStartupScene());
    }
}
