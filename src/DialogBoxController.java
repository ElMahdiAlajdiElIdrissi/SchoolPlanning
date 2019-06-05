import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DialogBoxController {
    @FXML
    private Button confirmButton;

    public void okAction(ActionEvent actionEvent) throws IOException {
        ((Stage) confirmButton.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }
}
