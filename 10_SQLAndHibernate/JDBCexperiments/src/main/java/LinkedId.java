import javax.persistence.Column;
import java.io.Serializable;
import java.util.Objects;

public class LinkedId implements Serializable {

    @Column(name = "student_id")
    private int studentId;
    @Column(name = "course_id")
    private int courseId;

    public LinkedId() {
    }

    public LinkedId(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedId linkedId = (LinkedId) o;
        return studentId == linkedId.studentId &&
                courseId == linkedId.courseId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, courseId);
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
