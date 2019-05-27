package Views;

import Views.SceneManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button register;

    @FXML
    private void registerAction(ActionEvent ev) throws IOException {
        ((Stage)register.getScene().getWindow()).setScene(SceneManager.getRegisterScene());
    }
}


