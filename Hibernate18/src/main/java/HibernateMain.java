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
		Criteria criteria = session.createCriteria(User.class);
	
		user1 = (User)session.get(User.class, 1);
		/*Only one update statement will be executed. Check console*/
		user1.setFirstName("updated 1st time");
		user1.setFirstName("updated 2nd time");
		session.update(user1);
		user1.setFirstName("updated 3rd time");
		user1.setFirstName("updated 4th time");
		transaction.commit();
		session.close();
	}
}