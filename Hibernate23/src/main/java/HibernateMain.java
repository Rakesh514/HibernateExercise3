import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;

/*Inheritence - Table Per Class strategy*/

public class HibernateMain {
	private static SessionFactory factory;

	public static void main(String []args)
	{
		Vehicle vehicle = new Vehicle();
		vehicle.setId(1);
		vehicle.setName("Vehicle");
		
		TwoWheeler twoWheeler = new TwoWheeler();
		twoWheeler.setId(2);
		twoWheeler.setName("Two");
		twoWheeler.setType("Two Wheeler");
		
		FourWheeler fourWheeler = new FourWheeler();
		fourWheeler.setId(3);
		fourWheeler.setName("Four");
		fourWheeler.setType("Four Wheeler");
		
		try {
			factory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("Failed to create sessionFactory object." + ex);
		}

		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		session.save(vehicle);
		session.save(twoWheeler);
		session.save(fourWheeler);
		transaction.commit();
		session.close();
	}
}
