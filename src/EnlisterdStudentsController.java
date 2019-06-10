import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class EnlisterdStudentsController implements Initializable {
    @FXML
    private ListView table;

    private StringBuilder enlistedCourses = new StringBuilder();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBase db = new DataBase();
        try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
            PreparedStatement stm = conn.prepareStatement("select First_Name from student where Id = any(select Student_Id from ingeschrevencursussen where Cursus_Id = any(select Cursus_Id from toegewezencursus where Docent_Id = ?));")){
            stm.setInt(1, GlobalVars.getDocentId());
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                enlistedCourses.append(rs.getString(1) + "\n");
//                System.out.println(rs.getString(1));
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
        ObservableList<String> docentAndCursus = FXCollections.observableArrayList();
        docentAndCursus.add("These are the students already enlisted to your course: ");
        docentAndCursus.add(enlistedCourses.toString());
        table.setItems(docentAndCursus);
        table.setPrefSize(400,400);
    }

    @FXML
    private Button back;

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getDocentPlanning());
    }
}
