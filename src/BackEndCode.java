import models.entities.Student;
import models.dao.StudentDao;

import java.sql.SQLException;
import java.util.Scanner;

public class BackEndCode {
    private static int counter = 0;
    private static DataBase db = new DataBase();

    public static void main(String[] args) throws SQLException{
        db.executeScript("databasescript.txt");
        db.executeScript("InsertIntoDatabase.txt");

        StudentDao studentDao = new StudentDao( db.getURL(), db.getUSERNAME(), db.getPASSWORD());
        Student student = studentDao.getStudentByID(1);

        System.out.println(student.getFirstName() + " " + student.getLastName());
    }


    private static String sortData(String sortOnWhat) {
        String giveBack = "SELECT ".concat(sortOnWhat).concat(" from Beers order by ").concat(sortOnWhat);
        if (counter < 2) {
            switch (counter) {
                case 0:
                    giveBack.concat(" asc");
                    counter++;
                    break;
                case 1:
                    giveBack.concat(" desc");
                    counter++;
                    break;
            }
        } else {
            counter = 0;
        }
        return giveBack;
    }

    private static String dataInput(){
        System.out.println("Please type the columnname of what you'd like sorted");
        Scanner keyboard = new Scanner(System.in);
        return keyboard.nextLine();
    }
}
