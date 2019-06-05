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
    private TextField userNameLogin;

    @FXML
    private PasswordField passWordLogin;

    public void loginAction(ActionEvent ev) throws IOException {
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

    @FXML
    private void registerAction(ActionEvent ev) throws IOException {
        ((Stage) register.getScene().getWindow()).setScene(SceneManager.getRegisterScene());
    }
}
