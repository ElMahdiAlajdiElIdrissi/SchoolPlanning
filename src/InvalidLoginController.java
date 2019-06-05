import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class InvalidLoginController {
    @FXML
    private Button goBackButton;


    public void goBack(ActionEvent actionEvent) throws IOException {
        ((Stage) goBackButton.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }
}
