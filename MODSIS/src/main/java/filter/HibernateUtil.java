package filter;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {
	private static SessionFactory fabrica;

	public static SessionFactory getSessionFactory() {
		if (fabrica == null) {
			try {
				AnnotationConfiguration ac = new AnnotationConfiguration();
				ac.configure("hibernate.cfg.xml");
				fabrica = ac.configure().buildSessionFactory();
			} catch (Throwable ex) {
				// Log the exception.
				System.err.println("Initial SessionFactory creation failed." + ex);
				throw new ExceptionInInitializerError(ex);
			}
			return fabrica;
		} else {
			return fabrica;
		}

	}

}
