import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/*Difference between Update and Merge methods*/

public class HibernateMain {
	private static SessionFactory factory;
	public static void main(String[] args)
	{
		try {
			factory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		}

		User2 user = new User2();
		user.setId(1);
		user.setName("test1");
		Session session0 = factory.openSession();
		Transaction transaction0 = session0.beginTransaction();
		session0.save(user);
		transaction0.commit();
		session0.close();
		
		Session session = factory.openSession();
		//Transaction transaction = session.beginTransaction();
		User2 user1 = (User2)session.load(User2.class, 1);
		//transaction.commit();
		session.close();  
		
		user1.setName("New Value");
		
		Session session2 = factory.openSession();
		Transaction transaction2 = session2.beginTransaction();
		session2.merge(user1);
		transaction2.commit();
		session2.close();
	}
}
