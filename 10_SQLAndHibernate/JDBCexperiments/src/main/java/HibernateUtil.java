import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {
     private static SessionFactory sessionFactory;

     static {

         final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();

         try {
             Metadata metadata = new MetadataSources(registry).getMetadataBuilder().build();
             sessionFactory = metadata.getSessionFactoryBuilder().build();
         }catch (Exception e) {
             StandardServiceRegistryBuilder.destroy(registry);
         }
     }

     public static SessionFactory getSessionFactory() {
         return sessionFactory;
     }
}
