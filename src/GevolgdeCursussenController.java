import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class GevolgdeCursussenController implements Initializable {


    @FXML
    private TextField searchField;

    @FXML
    private ListView table;

    @FXML
    private void searchDocentOrCursus(ActionEvent event)throws IOException {
        DataBase db = new DataBase();
        String search = searchField.getText().trim();
        String queryDocent = "SELECT DISTINCT Voor_Naam, Achter_Naam, Naam_Cursus, Start_Datum, Eind_Datum FROM Docent " +
                "INNER JOIN Cursus ON docent.id = cursus.id WHERE Voor_Naam=? OR Achter_Naam=? OR Naam_Cursus=?;";

        try (Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
             PreparedStatement st = conn.prepareStatement(queryDocent)) {
            st.setString(1, search);
            st.setString(2, search);
            st.setString(3, search);
            ResultSet rs = st.executeQuery();

            ObservableList<String> docentAndCursus = FXCollections.observableArrayList();
            while(rs.next()) {
                int columnCount = rs.getMetaData().getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    docentAndCursus.add(rs.getString(i) + " ");
                }
            }
            if (docentAndCursus.isEmpty()) {
                SceneManager.alertError("The teacher/course you were looking for doesn't exist");
                docentAndCursus.add("These are the courses you already enlisted in: ");
                docentAndCursus.add(enlistedCourses.toString());
            }
            table.setItems(docentAndCursus);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private Button back;

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }

    private StringBuilder enlistedCourses = new StringBuilder();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBase db = new DataBase();
        try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
            PreparedStatement stm = conn.prepareStatement("SELECT cursus.Naam_Cursus from ingeschrevencursussen join cursus on ingeschrevencursussen.Cursus_Id = cursus.Id where Student_Id = ?;")){
            stm.setInt(1, GlobalVars.getStudentId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                enlistedCourses.append(rs.getString(1) + " ");
//                System.out.println(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
}
