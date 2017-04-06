/*Second level cache*/
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.CacheConcurrencyStrategy;
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

		
		/*Only one get statement will be executed for the below two get statements in different sessions. Check console*/
		/*Changes made to enable second level cache:
		 * 1) Changed the value of property, "cache.provider_class" in 'hibernate.cfg.xml' to a cache provider name say, 'EhCacheProvider' from 'NoCacheProvider' which is default.
		   2) Add a new property to switch on second level cache. Add this line - '<property name="cache.use_second_level_cache">true</property>'.
		   3) Download and add the jar for EHCache or add a dependency in pom file if it is a maven project. 
		   4) Add '@Cacheable' for 'User' class.
		   5) Add '@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)'.
		 */ 
		session = factory.openSession();
		transaction = session.beginTransaction();
		user1 = (User)session.get(User.class, 1);
		transaction.commit();
		session.close();
		
		Session session2 = factory.openSession();
		Transaction transaction2 = session2.beginTransaction();
		user1 = (User)session2.get(User.class, 1);
		transaction2.commit();
		session2.close();

	}
}