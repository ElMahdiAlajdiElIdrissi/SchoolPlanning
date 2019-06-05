import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button register;

    @FXML
    private Button login;

    @FXML
    private TextField userNameLogin;

    @FXML
    private PasswordField passWordLogin;

    public void loginAction(ActionEvent ev) throws IOException {
        if (userNameLogin.getText().equals(passWordLogin.getText())) {
            ((Stage) login.getScene().getWindow()).setScene(SceneManager.getSuccesfullLogin());
            System.out.println("Login succesfull!");
        } else {
            System.out.println("Invalid username or password");
            ((Stage) login.getScene().getWindow()).setScene(SceneManager.getInvalidMessage());
        }
    }

    @FXML
    private void registerAction(ActionEvent ev) throws IOException {
        ((Stage) register.getScene().getWindow()).setScene(SceneManager.getRegisterScene());
    }
}
