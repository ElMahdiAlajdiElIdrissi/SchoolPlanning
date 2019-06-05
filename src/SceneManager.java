import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {


    public static Scene getSchoolPlanningScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("SchoolPlanning.fxml"));
        return new Scene(root, 425, 350);
    }

    public static Scene getGevolgdeCursussenScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("GevolgdeCursussen.fxml"));
        return new Scene(root, 500, 450);
    }

    public static Scene getInschrijvenCursussen() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("InschrijvenCursussen.fxml"));
        return new Scene(root, 490, 300);
    }

    public static Scene getLoginScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "Login.fxml"));
        return new Scene(root, 350, 150);
    }

    public static Scene getRegisterScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "Register.fxml"));
        return new Scene(root, 400, 250);
    }

    public static Scene getSuccesfullLogin() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "DialogBox.fxml"));

        return new Scene(root, 250, 150);
    }

    public static Scene getInvalidMessage() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "InvalidLogin.fxml"));
        return new Scene(root, 250, 150);
    }
}
