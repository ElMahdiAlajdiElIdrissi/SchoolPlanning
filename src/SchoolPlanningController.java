import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class SchoolPlanningController {

    @FXML
    private Button gevolgdeCursussen;

    @FXML
    private void clickedOnGevolgdeCursussen(ActionEvent ev) throws IOException {
        ((Stage) gevolgdeCursussen.getScene().getWindow()).setScene(SceneManager.getGevolgdeCursussenScene());
    }

    @FXML
    private Button inschrijvenCursussen;

    @FXML
    private void clickedOnInchrijvenCursussen(ActionEvent ev) throws IOException {
        ((Stage) inschrijvenCursussen.getScene().getWindow()).setScene(SceneManager.getInschrijvenCursussen());
    }

    @FXML
    private Button logout;

    @FXML
    private void logout(ActionEvent ev) throws IOException {
        ((Stage) logout.getScene().getWindow()).setScene(SceneManager.getStartupScene());
    }
}
