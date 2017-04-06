/*Named Query*/

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateMain {
	private static SessionFactory factory;

	public static void main(String[] args) {
		try {

			factory = new AnnotationConfiguration().configure()
					.buildSessionFactory();

		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		}

		User user1 = new User();
		user1.setFirstName("Rakesh1");
		user1.setLastName("Kumar1");
		
		User user2 = new User();
		user2.setFirstName("Rakesh2");
		user2.setLastName("Kumar2");
		
		User user3 = new User();
		user3.setFirstName("Rakesh3");
		user3.setLastName("Kumar3");
		
		User user4 = new User();
		user4.setFirstName("Rakesh4");
		user4.setLastName("Kumar4");
		
		User user5 = new User();
		user5.setFirstName("Rakesh5");
		user5.setLastName("Kumar5");
			
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user1);
		session.save(user2);
		session.save(user3);
		session.save(user4);
		session.save(user5);
		transaction.commit();
		session.close();

		session = factory.openSession();
		transaction = session.beginTransaction();
		Query qry = session.getNamedQuery("User.byFirstName");
		qry.setString(0, "Rakesh1");
		System.out.println("Number of users with first name as 'Rakesh1': " + qry.list().size());
		User u = (User)qry.list().get(0);
		System.out.println("Last name: " + u.getLastName());
		transaction.commit();
		session.close();
	}
}