package login;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    private Button register;
    @FXML
    private Button cancelRegistration;

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

    @FXML
    private void registerAction(ActionEvent ev) throws IOException {
        ((Stage) register.getScene().getWindow()).setScene(SceneManager.getRegisterScene());
    }

    public void cancelRegistration(ActionEvent ev) throws IOException {
        ((Stage) cancelRegistration.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

    public void createUser(ActionEvent ev) throws IOException {
        if (passWord.getText().equals(confirmPassWord.getText())) {
            User user = new User(firstName.getText(), lastName.getText(), userName.getText(), passWord.getText());
            System.out.println(user);
        } else {
            System.out.println("Password must be the same");
        }
    }

    public void loginAction(ActionEvent ev) throws IOException {
        if (userName.getText().equals(passWord.getText())){
            System.out.println("Login succesfull!");
        } else {
            System.out.println("Invalid password");
        }
    }
}


