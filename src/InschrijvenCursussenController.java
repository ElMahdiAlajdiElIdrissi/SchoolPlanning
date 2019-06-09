import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.dao.IngeschrevenCursusDao;
import models.entities.Cursus;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.ResourceBundle;

public class InschrijvenCursussenController implements Initializable {

    @FXML
    private Button back;

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }

    @FXML
    private ChoiceBox<String> choiceBox = new ChoiceBox<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DataBase db = new DataBase();

        ObservableList<String> choices = FXCollections.observableArrayList();

        try (Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
             PreparedStatement statement = conn.prepareStatement("select * from cursus where Id <> all(select Cursus_Id from ingeschrevencursussen where ingeschrevencursussen.Student_Id = ?);")) {
            statement.setInt(1, GlobalVars.getStudentId());
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                String naam = rs.getString("Naam_Cursus");
                choices.add(naam);
            }
            choiceBox.getItems().addAll(choices);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    @FXML
    private TextField Gebruikersnaam;

    @FXML
    private TextField Wachtwoord;

    @FXML
    private void registerCourse(ActionEvent ev) throws IOException{
        DataBase db = new DataBase();
        IngeschrevenCursusDao icdao = new IngeschrevenCursusDao(db.getURL(), db.getUSERNAME(), db.getPASSWORD());

        List<Cursus> a = icdao.getAllNietIngeschrevenCursussen(GlobalVars.getStudentId());
        String course = choiceBox.getSelectionModel().getSelectedItem();
        String gn = Gebruikersnaam.getText().trim();
        String ww = Wachtwoord.getText().trim();

        for (int i = 0; i < a.size(); i++) {
            if(a.get(i).getNaamCursus().equals(course)){
                GlobalVars.setId(a.get(i).getId());
            }
        }

        String sql = "select Id, Gebruikers_Naam, Passwoord from Student where Id=? and Gebruikers_Naam=? and Passwoord=?";
        String sql2 = "insert ignore into ingeschrevencursussen(Student_Id, Cursus_Id) VALUES (?, ?)";

        if(!course.isEmpty() && !gn.isEmpty() && !ww.isEmpty() ){
            try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2)){
                stmt.setInt(1, GlobalVars.getStudentId());
                stmt.setString(2,gn);
                stmt.setString(3,ww);

                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        stmt2.setInt(1, GlobalVars.getStudentId());
                        stmt2.setInt(2, GlobalVars.getId());
                        stmt2.executeUpdate();
                    }
                }
                System.out.println("Successfully registered for the course");
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            System.out.println("Error registering in for course...");
        }
    }
}
