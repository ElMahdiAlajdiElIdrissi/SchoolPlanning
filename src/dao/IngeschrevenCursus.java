package dao;

import java.io.Serializable;

public class IngeschrevenCursus implements Serializable {
    private int cursusID;
    private int studentID;

    public int getCursusID() {
        return cursusID;
    }

    public void setCursusID(int cursusID) {
        this.cursusID = cursusID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IngeschrevenCursus{");
        sb.append("cursusID=").append(cursusID);
        sb.append(", studentID=").append(studentID);
        sb.append('}');
        return sb.toString();
    }
}
