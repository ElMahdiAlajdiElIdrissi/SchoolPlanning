package App.controllers;

import App.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {
    @FXML
    private Button register;

    @FXML
    private Button login;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField password;

    public void loginAction(ActionEvent ev) throws IOException {
        DataBase db = new DataBase();
        String un = userName.getText().trim();
        String pw = password.getText().trim();

        if(GlobalVars.isStudentOrDocent()){
            String sql = "select Id, User_Name, Password from Student where User_Name=? and Password=?";

            try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
                PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1, un);
                st.setString(2, pw);
                ResultSet rs = st.executeQuery();
                int count = 0;
                while(rs.next()){
                    GlobalVars.setStudentId(rs.getInt("Id"));
                    count++;
                }
                if(count==1){
                    SceneManager.alertVerify("Succesfully logged in!");
                    ((Stage) login.getScene().getWindow()).setScene(SceneManager.getSchoolScheduleScene());
                }else{
                    SceneManager.alertError("Invalid username or password!");
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            String sql = "select Id, User_Name, Password from Teacher where User_Name=? and Password=?";

            try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
                PreparedStatement st = conn.prepareStatement(sql)){
                st.setString(1, un);
                st.setString(2, pw);
                ResultSet rs = st.executeQuery();
                int count = 0;
                while(rs.next()){
                    GlobalVars.setDocentId(rs.getInt("Id"));
                    count++;
                }
                if(count==1){
                    SceneManager.alertVerify("Succesfully logged in!");
                    ((Stage) login.getScene().getWindow()).setScene(SceneManager.getTeacherSchedule());
                }else{
                    SceneManager.alertError("Invalid username or password!");
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }

    }

    @FXML
    private void registerAction(ActionEvent ev) throws IOException {
        ((Stage) register.getScene().getWindow()).setScene(SceneManager.getRegisterScene());
    }

    @FXML
    private Button back;

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getStartupScene());
    }

/*    @FXML
    private GridPane group;*/
    /*@FXML
    private HBox buttons;
    @FXML
    private VBox labels;
    @FXML
    private VBox inputs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //group.setAlignment(Pos.CENTER);
        labels.setAlignment(Pos.CENTER);
        inputs.setAlignment(Pos.TOP_CENTER);
        buttons.setSpacing(10);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
    }*/
}
