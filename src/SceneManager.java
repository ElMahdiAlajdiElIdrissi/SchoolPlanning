import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {


    public static Scene getSchoolPlanningScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("Views/SchoolPlanning.fxml"));
        return new Scene(root, 400, 250);
    }

    public static Scene getGevolgdeCursussenScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("Views/GevolgdeCursussen.fxml"));
        return new Scene(root, 430, 250);
    }

    public static Scene getInschrijvenCursussen() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("Views/InschrijvenCursussen.fxml"));
        return new Scene(root, 490, 300);
    }

    public static Scene getLoginScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "Views/Login.fxml"));
        return new Scene(root, 350, 150);
    }

    public static Scene getRegisterScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "Views/Register.fxml"));
        return new Scene(root, 400, 250);
    }
    public static Scene getSuccesfullLogin() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "Views/DialogBox.fxml"));

        return new Scene(root, 250, 150);
    }

    public static Scene getInvalidMessage() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "Views/InvalidLogin.fxml"));
        return new Scene(root, 250, 150);
    }
}
