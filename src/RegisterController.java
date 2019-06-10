import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class RegisterController {
    @FXML
    private Button cancelRegistration;

    @FXML
    private Button createUser;

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


    public void createUser(ActionEvent ev) throws IOException {
        DataBase db = new DataBase();
        String un = userName.getText().trim();
        String fn = firstName.getText().trim();
        String ln = lastName.getText().trim();
        String pw = passWord.getText().trim();
        String cpw = confirmPassWord.getText().trim();

        if(GlobalVars.isStudentOrDocent()){
            String sql = "select Gebruikers_Naam from Student where Gebruikers_Naam=?";
            String sql2 = "insert ignore into student(First_Name , Last_Name, Gebruikers_Naam, Passwoord) VALUES (?, ?, ?, ?)";

            try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2)){

                stmt.setString(1,un);

                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        SceneManager.alertError("That user already exits!");
                    }else{
                        if(pw.equals(cpw)){
                            stmt2.setString(1,fn);
                            stmt2.setString(2,ln);
                            stmt2.setString(3,un);
                            stmt2.setString(4,pw);
                            stmt2.executeUpdate();
                            SceneManager.alertVerify("You have registered successfully");
                        }else {
//                        System.out.println("Error: passwords do not match");
                            SceneManager.alertError("Please enter matching passwords!");
                        }
                    }
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }else{
            String sql = "select Gebruikers_Naam from Docent where Gebruikers_Naam=?";
            String sql2 = "insert ignore into Docent(Voor_Naam , Achter_Naam, Departement_Id, Password, Gebruikers_Naam) VALUES (?, ?, ?, ?, ?)";

            try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
                PreparedStatement stmt = conn.prepareStatement(sql);
                PreparedStatement stmt2 = conn.prepareStatement(sql2)){

                stmt.setString(1,un);

                try(ResultSet rs = stmt.executeQuery()){
                    if(rs.next()){
                        SceneManager.alertError("That user already exits!");
                    }else{
                        if(pw.equals(cpw)){
                            stmt2.setString(1,fn);
                            stmt2.setString(2,ln);
                            stmt2.setInt(3,1);
                            stmt2.setString(4,pw);
                            stmt2.setString(5,un);
                            stmt2.executeUpdate();
                            SceneManager.alertVerify("You have registered successfully");
                        }else {
//                        System.out.println("Error: passwords do not match");
                            SceneManager.alertError("Please enter matching passwords!");
                        }
                    }
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        ((Stage) createUser.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

    public void cancelRegistration(ActionEvent ev) throws IOException {
        ((Stage) cancelRegistration.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

}
