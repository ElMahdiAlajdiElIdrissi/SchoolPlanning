import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import models.dao.StudentDao;
import models.entities.Student;

import java.awt.*;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Optional;

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
        StudentDao sdao = new StudentDao(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
        List<Student> a = sdao.getAllStudents();
        String un = userName.getText().trim();
        String fn = firstName.getText().trim();
        String ln = lastName.getText().trim();
        String pw = passWord.getText().trim();
        String cpw = confirmPassWord.getText().trim();
        String sql = "select Gebruikers_Naam from Student where Gebruikers_Naam=?";
        String sql2 = "insert ignore into student(Id, First_Name , Last_Name, Gebruikers_Naam, Passwoord) VALUES (?, ?, ?, ?, ?)";

        try(Connection conn = DriverManager.getConnection(db.getURL(), db.getUSERNAME(), db.getPASSWORD());
            PreparedStatement stmt = conn.prepareStatement(sql);
            PreparedStatement stmt2 = conn.prepareStatement(sql2)){

            stmt.setString(1,un);

            try(ResultSet rs = stmt.executeQuery()){
                if(rs.next()){
//                    System.out.println("Error: User exists");
                    SceneManager.alertError("That user already exits!");
                }else{
                    if(pw.equals(cpw)){
                        stmt2.setInt(1,a.size()+1);
                        stmt2.setString(2,fn);
                        stmt2.setString(3,ln);
                        stmt2.setString(4,un);
                        stmt2.setString(5,pw);
                        stmt2.executeUpdate();
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

    public void cancelRegistration(ActionEvent ev) throws IOException {
        ((Stage) cancelRegistration.getScene().getWindow()).setScene(SceneManager.getLoginScene());
    }

}
