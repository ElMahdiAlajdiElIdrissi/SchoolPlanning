package app.models.entities;

import java.io.Serializable;

/**
 * public class for getters and setters from an Object which exists in our database
 */
public class Department implements Serializable {
    private int id;
    private String departmentName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Department{");
        sb.append("id=").append(id);
        sb.append(", departmentName='").append(departmentName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
