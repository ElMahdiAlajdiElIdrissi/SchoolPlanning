
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class SchoolApp extends Application {
    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Views/Login.fxml"));
        primaryStage.setTitle("Login");
        primaryStage.setScene(SceneManager.getLoginScene());
        primaryStage.show();
    }
}
