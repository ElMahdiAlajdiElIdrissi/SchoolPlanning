package app.models.entities;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String courseName;
    private String startDate;
    private String endDate;
    private int departmentId;

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentID(int departmentID) {
        this.departmentId = departmentID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String CourseName) {
        this.courseName = CourseName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Course{");
        sb.append("id=").append(id);
        sb.append(", courseName='").append(courseName).append('\'');
        sb.append(", startDate='").append(startDate).append('\'');
        sb.append(", endDate='").append(endDate).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
