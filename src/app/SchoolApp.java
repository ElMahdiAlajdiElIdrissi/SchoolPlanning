package app;

import app.controllers.DataBase;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * this is the main-application class which we use to start the program
 * extends Application in order to run JavaFX
 */
public class SchoolApp extends Application {
    /**
     * main-method in which we create our database tables and fill them with the necessary base objects, followed by the FX-launch
     * @param args
     */
    public static void main(String[] args) {
        DataBase db = new DataBase();
        db.executeScript("src\\app\\DatabaseScript.txt");
        db.executeScript("src\\app\\InsertIntoDatabase.txt");
        launch(args);
    }

    /**
     * override-methode for creating our primaryStage window
     * @param primaryStage
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SchoolPlanner");
        primaryStage.setScene(SceneManager.getStartupScene());
        primaryStage.show();
    }
}
