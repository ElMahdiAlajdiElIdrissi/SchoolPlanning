package App.models.entities;

import java.io.Serializable;

public class Teacher implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int departmentId;
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getDepartmentID() {
        return departmentId;
    }

    public void setDepartmentID(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Teacher{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", departmentId=").append(departmentId);
        sb.append('}');
        return sb.toString();
    }
}
