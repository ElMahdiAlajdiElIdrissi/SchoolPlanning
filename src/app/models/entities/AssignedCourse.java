package app.models.entities;

import java.io.Serializable;

public class AssignedCourse implements Serializable {
    private int courseId;
    private int teacherId;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AssignedCourse{");
        sb.append("courseId=").append(courseId);
        sb.append(", teacherId=").append(teacherId);
        sb.append('}');
        return sb.toString();
    }
}
