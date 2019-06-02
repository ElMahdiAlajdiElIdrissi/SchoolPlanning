import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class SchoolappController {
    @FXML
    private Button gevolgdeCursussen;

    @FXML
    private Button back;

    @FXML
    private Button inschrijvenCursussen;

    @FXML
    private TextField textFieldGebruikersnaam;

    @FXML
    private TextField textFieldWachtwoord;

    @FXML
    private TableView table;

    @FXML
    private void clickedOnGevolgdeCursussen(ActionEvent ev) throws IOException {
        ((Stage) gevolgdeCursussen.getScene().getWindow()).setScene(SceneManager.getGevolgdeCursussenScene());
    }

    @FXML
    private void clickedOnInchrijvenCursussen(ActionEvent ev) throws IOException {
        ((Stage) inschrijvenCursussen.getScene().getWindow()).setScene(SceneManager.getInschrijvenCursussen());
    }

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }
    @FXML
    private void setTable (){

    }
}
