package App;

import App.controllers.DataBase;
import javafx.application.Application;
import javafx.stage.Stage;

public class SchoolApp extends Application {

    public static void main(String[] args) {
        DataBase db = new DataBase();
        db.executeScript("src\\App\\DatabaseScript.txt");
        db.executeScript("src\\App\\InsertIntoDatabase.txt");
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("src\\App.views\\Startup.fxml"));
        primaryStage.setTitle("SchoolPlanner");
        primaryStage.setScene(SceneManager.getStartupScene());
        primaryStage.show();
    }
}
