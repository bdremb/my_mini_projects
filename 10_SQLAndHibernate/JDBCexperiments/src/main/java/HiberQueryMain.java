import entity.Course;
import entity.Purchaselist;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class HiberQueryMain {

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {

            Session session = sessionFactory.openSession();

            CriteriaBuilder builder = session.getCriteriaBuilder();


            CriteriaQuery<Student> studentCriteriaQuery = builder.createQuery(Student.class);
            Root<Student> studentRoot = studentCriteriaQuery.from(Student.class);
            studentCriteriaQuery.select(studentRoot);

            List<Student> studentList = session.createQuery(studentCriteriaQuery).getResultList();

            for (Student st : studentList) {
                System.out.println(st.getName() + "   " + st.getAge());

            }

            CriteriaQuery<Course> query = builder.createQuery(Course.class);
            Root<Course> root = query.from(Course.class);
            query.select(root).where(builder.greaterThan(root.<Integer>get("price"), 100000))
                    .orderBy(builder.desc(root.get("price")));

            CriteriaQuery<Purchaselist> purchQuery = builder.createQuery(Purchaselist.class);
            Root<Purchaselist> purchRoot = purchQuery.from(Purchaselist.class);

            List<Course> courseList = session.createQuery(query).setMaxResults(5).getResultList();

            for (Course course : courseList) {
                System.out.println(course.getName() + " " + course.getPrice());
            }

        }
    }
}
