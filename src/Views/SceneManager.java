package login;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {
    public static Scene getLoginScene() throws IOException {
        Parent root = FXMLLoader.load(LoginApp.class.getResource(
                "Login.fxml"));
        return new Scene(root, 350, 150);
    }

    public static Scene getRegisterScene() throws IOException {
        Parent root = FXMLLoader.load(LoginApp.class.getResource(
                "Register.fxml"));
        return new Scene(root, 400, 250);
    }
}
