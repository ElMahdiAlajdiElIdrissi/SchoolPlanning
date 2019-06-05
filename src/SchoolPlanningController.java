import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SchoolPlanningController {

    @FXML
    private Button gevolgdeCursussen;

    @FXML
    private Button back;

    @FXML
    private void clickedOnGevolgdeCursussen(ActionEvent ev) throws IOException {
        DataBase db = new DataBase();
        try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
            PreparedStatement stm = conn.prepareStatement("SELECT cursus.Naam_Cursus from ingeschrevencursussen join cursus on ingeschrevencursussen.Cursus_Id = cursus.Id where Student_Id = ?;")){
            stm.setInt(1, GlobalVars.getStudentId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }

        ((Stage) gevolgdeCursussen.getScene().getWindow()).setScene(SceneManager.getGevolgdeCursussenScene());
    }

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }

    @FXML
    private Button inschrijvenCursussen;

    @FXML
    private void clickedOnInchrijvenCursussen(ActionEvent ev) throws IOException {
        ((Stage) inschrijvenCursussen.getScene().getWindow()).setScene(SceneManager.getInschrijvenCursussen());
    }
}
