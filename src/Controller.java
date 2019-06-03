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

public class Controller {

    @FXML
    private Button back;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField userNameLogin;

    @FXML
    private PasswordField passWordLogin;

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
    private Button goBackButton;

    @FXML
    private Button gevolgdeCursussen;

    @FXML
    private Button inschrijvenCursussen;

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
        if (userNameLogin.getText().equals(passWordLogin.getText())) {
            ((Stage) register.getScene().getWindow()).setScene(SceneManager.getSuccesfullLogin());
            System.out.println("Login succesfull!");
        } else {
            System.out.println("Invalid username or password");
            ((Stage) register.getScene().getWindow()).setScene(SceneManager.getInvalidMessage());
        }
    }

    public void confirmLogin(ActionEvent actionEvent) {
        System.out.println("Login succes");
    }

    public void okAction(ActionEvent actionEvent) throws IOException {
        ((Stage) confirmButton.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }


    public void goBack(ActionEvent actionEvent) throws IOException {
        ((Stage) goBackButton.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }


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
}


