package app.controllers;

import app.SceneManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * public class which controls our StartupControllerFXML
 */
public class StartupController implements Initializable {

    @FXML
    private Button student;

    @FXML
    private Button teacher;

    /**
     * private method for progressing towards the right LoginScene when clicked on the Student button
     * @throws IOException
     */
    @FXML
    private void viewStudent() throws IOException {
        GlobalVars.setStudentOrDocent(true);
        ((Stage) student.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

    /**
     * private method for progressing towards the right LoginScene when clicked on the Teacher button
     * @throws IOException
     */
    @FXML
    private void viewTeacher() throws IOException{
        GlobalVars.setStudentOrDocent(false);
        ((Stage) teacher.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

    @FXML
    private GridPane group;

    /**
     * public method just for getting our allignment auto-centered
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group.setAlignment(Pos.CENTER);
    }
}
