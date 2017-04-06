/*Criteria API - About Projections*/
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
		Criteria criteria = session.createCriteria(User.class);
	
		/*1*/
//		criteria.setProjection(Projections.property("firstName"));
//		List list = (ArrayList<User>)criteria.list();
//		System.out.println("List size: " + list.size());
//		System.out.println("List: " + list.toString());
		
		/*2*/
//		criteria.setProjection(Projections.max("id"));
//		List list = (ArrayList<User>)criteria.list();
//		System.out.println("List size: " + list.size());
//		System.out.println("List: " + list.toString());
		
		/*3*/
		criteria.addOrder(Order.desc("id"));
		List list = (ArrayList<User>)criteria.list();
		User u = (User)list.get(0);
		System.out.println("List(0): " + u.getId());
		u = (User)list.get(1);
		System.out.println("List(1): " + u.getId());
		
		/*Please refer 'Query By Example' and add examples*/
		 
		transaction.commit();
		session.close();
	}
}