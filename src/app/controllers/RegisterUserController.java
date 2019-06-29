package app.controllers;

import app.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

/**
 * /**
 *  * public class which controls our RegisterUserFXML
 *  */
public class RegisterUserController {
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
    private PasswordField password;

    @FXML
    private PasswordField confirmPassword;

    /**
     * public method used for creating a new Student user
     * @param ev
     * @throws IOException
     */
    public void createUser(ActionEvent ev) throws IOException {
        DataBase db = new DataBase();
        String un = userName.getText().trim().equals("")?null:userName.getText().trim();
        String fn = firstName.getText().trim().equals("")?null:firstName.getText().trim();
        String ln = lastName.getText().trim().equals("")?null:lastName.getText().trim();
        String pw = password.getText().trim().equals("")?null:password.getText().trim();
        String cpw = confirmPassword.getText().trim().equals("")?null:confirmPassword.getText().trim();

        if(!(un == null || fn == null || ln == null || pw == null || cpw == null)){
            if(GlobalVars.isStudentOrDocent()){
                String sql = "select User_Name from Student where User_Name=?";
                String sql2 = "insert ignore into student(First_Name , Last_Name, User_Name, Password) VALUES (?, ?, ?, ?)";

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
                                SceneManager.alertError("Please enter matching passwords!");
                            }
                        }
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }else{
                String sql = "select User_Name from Teacher where User_Name=?";
                String sql2 = "insert ignore into Teacher(First_Name , Last_Name, Department_Id, User_Name, Password) VALUES (?, ?, ?, ?, ?)";

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
                                stmt2.setString(4,un);
                                stmt2.setString(5,pw);
                                stmt2.executeUpdate();
                                SceneManager.alertVerify("You have registered successfully");
                            }else {
                                SceneManager.alertError("Please enter matching passwords!");
                            }
                        }
                    }
                }catch(SQLException ex){
                    ex.printStackTrace();
                }
            }
            ((Stage) createUser.getScene().getWindow()).setScene(SceneManager.getLoginScene());
        }else{
            SceneManager.alertError("Please fill in all fields!");
        }
    }

    /**
     * private method for the cancel button which will bring us back to the LoginScene
     * @param ev
     * @throws IOException
     */
    public void cancelRegistration(ActionEvent ev) throws IOException {
        ((Stage) cancelRegistration.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

}
