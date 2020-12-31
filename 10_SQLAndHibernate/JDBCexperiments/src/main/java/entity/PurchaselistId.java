package entity;

import java.io.Serializable;
import java.util.Objects;

public class PurchaselistId implements Serializable {
    private String studentName;
    private String courseName;

    public PurchaselistId() {
    }

    public PurchaselistId(String studentName, String courseName) {
        this.studentName = studentName;
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaselistId that = (PurchaselistId) o;
        return Objects.equals(studentName, that.studentName) &&
                Objects.equals(courseName, that.courseName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentName, courseName);
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
