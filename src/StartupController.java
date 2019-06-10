import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StartupController implements Initializable {

    @FXML
    private Button student;

    @FXML
    private Button docent;

    @FXML
    private void viewStudent() throws IOException {
        GlobalVars.setStudentOrDocent(true);
        ((Stage) student.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

    @FXML
    private void viewDocent() throws IOException{
        GlobalVars.setStudentOrDocent(false);
        ((Stage) docent.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

    @FXML
    private GridPane group;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        group.setAlignment(Pos.CENTER);
    }
}