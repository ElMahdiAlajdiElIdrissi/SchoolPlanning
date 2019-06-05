import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterController {
    @FXML
    private Button cancelRegistration;

    @FXML
    private Button createUser;
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passWord;

    @FXML
    private PasswordField confirmPassWord;


    public void createUser(ActionEvent ev) throws IOException {
        if (passWord.getText().equals(confirmPassWord.getText())) {
            User user = new User(firstName.getText(), lastName.getText(), userName.getText(), passWord.getText());
            System.out.println(user);
        } else {
            System.out.println("Password must be the same");
        }
    }

    public void cancelRegistration(ActionEvent ev) throws IOException {
        ((Stage) cancelRegistration.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

}
