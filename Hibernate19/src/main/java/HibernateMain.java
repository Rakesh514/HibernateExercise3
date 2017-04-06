/*First level cache*/
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
		user1.setId(1);
		user1.setFirstName("Rakesh1");
		user1.setLastName("Kumar1");
		
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(user1);
		
		transaction.commit();
		session.close();

		session = factory.openSession();
		transaction = session.beginTransaction();
		
		/*1 - Only one get statement will be executed. Check console*/
//		user1 = (User)session.get(User.class, 1);
//		user1 = (User)session.get(User.class, 1);
//		user1 = (User)session.get(User.class, 1);
//		user1 = (User)session.get(User.class, 1);
		
		/*2 - Only one get statement will be executed and of course one update statement will be executed. Check console*/
		user1 = (User)session.get(User.class, 1);
		user1.setFirstName("Updated 1st time");
		user1 = (User)session.get(User.class, 1);
		
		transaction.commit();
		session.close();
		System.out.println("User1 first name: " + user1.getFirstName());
		
		/* The changes are persisted into database even though session.update() was not executed. 
		   Uncommenting the below lines shows the updated value. I do not think this behaviour has got something to do with caching. 
		   This is because the object fetched from the database is persistant. */
//		session = factory.openSession();
//		transaction = session.beginTransaction();
//		user1 = (User)session.get(User.class, 1);
//		System.out.println("User1 first name: " + user1.getFirstName());
//		transaction.commit();
//		session.close();
	}
}