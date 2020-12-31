package entity;

import entity.Student;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(SubscriptionId.class)
@Table(name = "Subscriptions")
public class Subscription {

    @ManyToOne
    @Id
    @JoinColumn(name = "student_id")  //
    private Student student;

    @Id
    @Column(name = "course_id")  //
    private int courseId;


    @Column(name = "subscription_date", insertable = false, updatable = false)
    private Date subscriptionDate;


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

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }


}
