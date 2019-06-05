import Views.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class Controller {

    @FXML
    private Button back;

    @FXML
    private Button login;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField userNameLogin;

    @FXML
    private PasswordField passWordLogin;

    @FXML
    private Button register;

    @FXML
    private Button cancelRegistration;

    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField passWord;

    @FXML
    private PasswordField confirmPassWord;

    @FXML
    private Button goBackButton;

    @FXML
    private Button gevolgdeCursussen;

    @FXML
    private Button inschrijvenCursussen;

    @FXML
    private void registerAction(ActionEvent ev) throws IOException {
        ((Stage) register.getScene().getWindow()).setScene(SceneManager.getRegisterScene());
    }

    public void cancelRegistration(ActionEvent ev) throws IOException {
        ((Stage) cancelRegistration.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

    public void createUser(ActionEvent ev) throws IOException {
        if (passWord.getText().equals(confirmPassWord.getText())) {
            User user = new User(firstName.getText(), lastName.getText(), userName.getText(), passWord.getText());
            System.out.println(user);
        } else {
            System.out.println("Password must be the same");
        }
    }

    public void loginAction(ActionEvent ev) throws IOException {
      /*  if (userNameLogin.getText().equals(passWordLogin.getText())) {
            ((Stage) login.getScene().getWindow()).setScene(SceneManager.getSuccesfullLogin());
            System.out.println("Login succesfull!");
        } else {
            System.out.println("Invalid username or password");
            ((Stage) login.getScene().getWindow()).setScene(SceneManager.getInvalidMessage());
        }*/
      DataBase db = new DataBase();
      String un = userNameLogin.getText().trim();
      String pw = passWordLogin.getText().trim();
      String sql = "select Gebruikers_Naam, Passwoord from Student where Gebruikers_Naam=? and Passwoord=?";

      try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
            PreparedStatement st = conn.prepareStatement(sql)){
          st.setString(1, un);
          st.setString(2, pw);
          ResultSet rs = st.executeQuery();
          int count = 0;
          while(rs.next()){
              count++;
          }
              if(count==1){
                  ((Stage) login.getScene().getWindow()).setScene(SceneManager.getSuccesfullLogin());
                  System.out.println("Login succesfull!");
              }else{
                  System.out.println("Invalid username or password");
                  ((Stage) login.getScene().getWindow()).setScene(SceneManager.getInvalidMessage());
              }
      }catch(SQLException ex){
              ex.printStackTrace();
      }
    }

    public void confirmLogin(ActionEvent actionEvent) {
        System.out.println("Login succes");
    }

    public void okAction(ActionEvent actionEvent) throws IOException {
        ((Stage) confirmButton.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }


    public void goBack(ActionEvent actionEvent) throws IOException {
        ((Stage) goBackButton.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }


    @FXML
    private void clickedOnGevolgdeCursussen(ActionEvent ev) throws IOException {
        ((Stage) gevolgdeCursussen.getScene().getWindow()).setScene(SceneManager.getGevolgdeCursussenScene());
    }

    @FXML
    private void clickedOnInchrijvenCursussen(ActionEvent ev) throws IOException {
        ((Stage) inschrijvenCursussen.getScene().getWindow()).setScene(SceneManager.getInschrijvenCursussen());
    }

    @FXML
    private void back(ActionEvent ev) throws IOException {
        ((Stage) back.getScene().getWindow()).setScene(SceneManager.getSchoolPlanningScene());
    }
}


