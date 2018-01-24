package com.august.saborrioweb.filter;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
//import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory fabrica;

    public static SessionFactory getSessionFactory()
    {
        if (fabrica == null)
        {
            try
            {
                AnnotationConfiguration ac = new AnnotationConfiguration();
                ac.configure("hibernate.cfg.xml");
                fabrica = ac.configure().buildSessionFactory();
                //fabrica = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            } catch (Throwable ex)
            {
                // Log the exception.
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
            }
            return fabrica;
        } 
        else
        {
            return fabrica;
        }

    }

}
