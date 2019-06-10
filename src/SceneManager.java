import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.util.Optional;

public class SceneManager {


    public static Scene getSchoolPlanningScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("views/SchoolPlanning.fxml"));
        return new Scene(root, 425, 350);
    }

    public static Scene getGevolgdeCursussenScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("views/GevolgdeCursussen.fxml"));
        return new Scene(root, 500, 450);
    }

    public static Scene getInschrijvenCursussen() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("views/InschrijvenCursussen.fxml"));
        return new Scene(root, 490, 300);
    }

    public static Scene getLoginScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "views/Login.fxml"));
        return new Scene(root, 350, 150);
    }

    public static Scene getRegisterScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "views/Register.fxml"));
        return new Scene(root, 400, 250);
    }

    public static Scene getSuccesfullLogin() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "views/DialogBox.fxml"));

        return new Scene(root, 250, 150);
    }

    public static Scene getInvalidMessage() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource(
                "views/InvalidLogin.fxml"));
        return new Scene(root, 250, 150);
    }

    public static void alertError(String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Something went wrong");
        alert.setContentText(text);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            ButtonType button = result.get();
        }
    }

    public static void alertVerify(String text){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("CONFIRMATION");
        alert.setHeaderText("Everything went ok!");
        alert.setContentText(text);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            ButtonType button = result.get();
        }
    }
}
