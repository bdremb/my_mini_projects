package entity;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionId implements Serializable {
    private Student student;
    private int courseId;

    public SubscriptionId() {
    }

    public SubscriptionId(Student student, int courseId) {
        this.student = student;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubscriptionId that = (SubscriptionId) o;
        return courseId == that.courseId &&
                Objects.equals(student, that.student);
    }

    @Override
    public int hashCode() {
        return Objects.hash(student, courseId);
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
