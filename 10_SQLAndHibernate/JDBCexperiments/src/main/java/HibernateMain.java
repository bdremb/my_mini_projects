import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class HibernateMain {
    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {

            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            Course course = session.get(Course.class, 1);
            Student student = session.get(Student.class, 37);
            Teacher teacher = session.get(Teacher.class, 25);

            Subscription subscription = session.get(Subscription.class,new SubscriptionId(student,1));


            System.out.println(subscription.getSubscriptionDate()
                    + " SUB " + subscription.getCourseId() + "  "
                    + subscription.getStudent().getName());
            System.out.println(course.getStudents().size());
            System.out.println(student.getStudSubscriptions().size());
            System.out.println(course.getName());

            for (Subscription sub : course.getSubscriptions()) {
                System.out.println(sub.getSubscriptionDate() + " "
                        + sub.getStudent().getName() + " "
                        + sub.getStudent().getId());
            }

            for (Subscription s : student.getStudSubscriptions()) {
                System.out.println(s.getSubscriptionDate() + "  "
                        + s.getStudent().getName() + "  "
                        + s.getStudent().getId());
            }
            System.out.println(teacher.getCourses().size());
            for (Course c : teacher.getCourses()) {
                System.out.println(c.getName());
            }

            transaction.commit();

            session.close();
        }
    }
}
