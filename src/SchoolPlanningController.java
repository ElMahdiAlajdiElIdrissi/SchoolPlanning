import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SchoolPlanningController {

    @FXML
    private Button gevolgdeCursussen;

    @FXML
    private TextField searchField;

    @FXML
    private TableColumn monday;

    @FXML
    private TableView table;

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
    private void searchDocentOrCursus(ActionEvent event)throws IOException {
        DataBase db = new DataBase();
        String search = searchField.getText().trim();
        String result = "";
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
                    result += rs.getString(i);
                    /*monday.setCellValueFactory(new PropertyValueFactory<String, String>(rs.getString(i) == null? " " : rs.getString(i)));*/
                    docentAndCursus.add(rs.getString(i) + " ");
                }
            }
            TableColumn extraView = new TableColumn();
            extraView.setText("Your search yielded following results: ");
            extraView.setCellValueFactory(new PropertyValueFactory<>(result));
            table.setItems(docentAndCursus);
            table.getColumns().addAll(extraView);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private Button inschrijvenCursussen;

    @FXML
    private void clickedOnInchrijvenCursussen(ActionEvent ev) throws IOException {
        ((Stage) inschrijvenCursussen.getScene().getWindow()).setScene(SceneManager.getInschrijvenCursussen());
    }

    @FXML
    private Button back;

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }
}
