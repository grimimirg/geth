package it.geth.core.utils;

import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingleSessionFactory {

    private SingleSessionFactory() {
    }

    private static class SingleSessionHelper {

        private static SessionFactory SESSION_FACTORY = null;

        private static SessionFactory getSingleSession(Yoda yoda) {

            if (SESSION_FACTORY == null) {
                Configuration hConf = new Configuration();

                for (Map.Entry<String, String> entry : yoda.getParams().entrySet()) {
                    hConf.setProperty(entry.getKey(), entry.getValue());
                }

                for (Class annotatedClass : yoda.getAnnotatedClasses()) {
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

    public static SessionFactory getInstance(Yoda yoda) {
        return SingleSessionHelper.getSingleSession(yoda);
    }

}
