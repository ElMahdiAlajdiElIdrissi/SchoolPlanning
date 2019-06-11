package app.controllers;

import app.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * public class which controls our TeacherScheduleFXML
 */
public class TeacherScheduleController {
    @FXML
    private Button assignedCourse;

    /**
     * private method for progressing to our TeacherAssignedCoursesScene by clicking on the button
     * @param ev
     * @throws IOException
     */
    @FXML
    private void assignedCourses(ActionEvent ev) throws IOException {
        ((Stage) assignedCourse.getScene().getWindow()).setScene(SceneManager.getTeacherAssignedCourses());
    }

    @FXML
    private Button enlistedStudent;

    /**
     * private method for progressing to our EnlistedStudentsScene by clicking on the button
     * @param ev
     * @throws IOException
     */
    @FXML
    private void enlistedStudents(ActionEvent ev) throws IOException {
        ((Stage) enlistedStudent.getScene().getWindow()).setScene(SceneManager.getEnlistedStudents());
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
