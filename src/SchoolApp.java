
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class SchoolApp extends Application {

    public static void main(String[] args) {
        DataBase db = new DataBase();
        db.executeScript("databasescript.txt");
        db.executeScript("InsertIntoDatabase.txt");
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("views/Startup.fxml"));
        primaryStage.setTitle("Choose your role");
        primaryStage.setScene(SceneManager.getStartupScene());
        primaryStage.show();
    }
}
