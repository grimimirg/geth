package it.geth.core;

import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingleSessionFactory {

    private SingleSessionFactory() {
    }

    private static class SingleSessionHelper {

        private static SessionFactory SESSION_FACTORY = null;

        private static SessionFactory getSingleSession(Descriptor databaseDescriptor) {

            if (SESSION_FACTORY == null) {
                Configuration hConf = new Configuration();

                for (Map.Entry<String, String> entry : databaseDescriptor.getParams().entrySet()) {
                    hConf.setProperty(entry.getKey(), entry.getValue());
                }

                for (Class annotatedClass : databaseDescriptor.getAnnotatedClasses()) {
                    hConf.addAnnotatedClass(annotatedClass);
                }

                try {
                    SESSION_FACTORY = hConf.buildSessionFactory();
                } catch (ExceptionInInitializerError ex) {
                    System.out.println(ex.getMessage());
                }
            }
            return SESSION_FACTORY;
        }
    }

    public static SessionFactory getInstance(Descriptor yoda) {
        return SingleSessionHelper.getSingleSession(yoda);
    }

}
