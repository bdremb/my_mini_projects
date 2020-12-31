import entity.Course;
import entity.CourseType;
import entity.Purchaselist;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HqlMain {
    private static List<Purchaselist> purchaselistList;
    private static Map<String, Integer> studentsMap;
    private static Map<String, Integer> coursesMap;
    private static final String HQL_PURCHASEALIST = "From " + Purchaselist.class.getSimpleName();
    private static final String HQL_STUDENTS = "SELECT name, id FROM entity.Student";
    private static final String HQL_COURSES = "SELECT name, id FROM entity.Course";

    public static void main(String[] args) {
        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory()) {
            Session session = sessionFactory.openSession();

//            purchaselistList = session.createQuery(HQL_PURCHASEALIST).getResultList();
//            coursesMap = putValuesToMap(HQL_COURSES, session);
//            studentsMap = putValuesToMap(HQL_STUDENTS, session);
            Transaction transaction = session.beginTransaction();
            Course course = new Course();
            course.setName("Новый курс");
            course.setType(CourseType.BUSINESS);
            course.setId(67);
            session.save(course);
            transaction.commit();

//            for (entity.Purchaselist purchaselist : purchaselistList) {
//                session = sessionFactory.openSession();
//
//
//                String studentName = purchaselist.getStudentName();
//                String courseName = purchaselist.getCourseName();
//
//                LinkedPurchaseList linkedPurchaseList = new LinkedPurchaseList(
//                        new LinkedId(studentsMap.get(studentName), coursesMap.get(courseName)), studentName, courseName);
//
//                session.save(linkedPurchaseList);
//                //transaction.commit();
//            }
            session.close();
        }
    }

    private static Map<String, Integer> putValuesToMap(String hqlQuery, Session session) {
        Map<String, Integer> mapResult = new HashMap<>();
        session.createQuery(hqlQuery).getResultList().forEach(e -> {
            Object[] nameAndId = (Object[]) e;
            mapResult.put((String) nameAndId[0], (Integer) nameAndId[1]);
        });
        return mapResult;
    }
}
