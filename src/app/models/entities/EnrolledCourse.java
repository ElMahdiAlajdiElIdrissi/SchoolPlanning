package app.models.entities;

import java.io.Serializable;

public class EnrolledCourse implements Serializable {
    private int courseId;
    private int studentId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EnrolledCourse{");
        sb.append("courseId=").append(courseId);
        sb.append(", studentId=").append(studentId);
        sb.append('}');
        return sb.toString();
    }
}
