import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class SceneManager {
    public static Scene getSchoolPlanningScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("SchoolPlanning.fxml"));
        return new Scene(root, 400, 250);
    }

    public static Scene getGevolgdeCursussenScene() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("GevolgdeCursussen.fxml"));
        return new Scene(root, 430, 250);
    }
    public static Scene getInschrijvenCursussen() throws IOException {
        Parent root = FXMLLoader.load(SchoolApp.class.getResource("InschrijvenCursussen.fxml"));
        return new Scene(root, 490, 300);
    }
}
