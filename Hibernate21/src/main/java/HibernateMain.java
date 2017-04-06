/*Query cache*/
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
		Query qry = session.createQuery("from User usr where usr.id = 1");
		qry.setCacheable(true);
		qry.list();
		transaction.commit();
		session.close();
		
		Session session2 = factory.openSession();
		Transaction transaction2 = session2.beginTransaction();
		Query qry2 = session2.createQuery("from User usr where usr.id = 1");
		qry2.setCacheable(true);
		qry2.list();
		transaction2.commit();
		session2.close();
	}
}