package it.geth.core;

import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingleSessionFactory {

    private SingleSessionFactory() {
    }

    private static class SingleSessionHelper {

        private static SessionFactory SESSION_FACTORY = null;

        private static SessionFactory getSingleSession(Descriptor descriptor) {

            if (SESSION_FACTORY == null) {
                Configuration configuration = new Configuration();

                for (Map.Entry<String, String> entry : descriptor.getParams().entrySet()) {
                    configuration.setProperty(entry.getKey(), entry.getValue());
                }

                for (Class annotatedClass : descriptor.getAnnotatedClasses()) {
                    configuration.addAnnotatedClass(annotatedClass);
                }

                try {
                    SESSION_FACTORY = configuration.buildSessionFactory();
                } catch (ExceptionInInitializerError ex) {
                    System.out.println(ex.getMessage());
                }
            }
            return SESSION_FACTORY;
        }
    }

    public static SessionFactory getInstance(Descriptor descriptor) {
        return SingleSessionHelper.getSingleSession(descriptor);
    }

}
