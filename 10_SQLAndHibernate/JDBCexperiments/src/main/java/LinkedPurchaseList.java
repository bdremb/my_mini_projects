import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "LinkedPurchaseList")
public class LinkedPurchaseList {

    @EmbeddedId
    private LinkedId linkedId;

    @Column(name = "student_name")
    private String studentName;

    @Column(name = "course_name")
    private String courseName;

    public LinkedPurchaseList(LinkedId linkedId, String studentName, String courseName) {
        this.linkedId = linkedId;
        this.studentName = studentName;
        this.courseName = courseName;
    }

    public LinkedPurchaseList() {

    }

    public LinkedId getLinkedId() {
        return linkedId;
    }

    public void setLinkedId(LinkedId linkedId) {
        this.linkedId = linkedId;
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
