package it.geth.core;

import it.geth.core.config.Descriptor;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SingleSessionFactory
{

    /**
     *
     */
    private SingleSessionFactory()
    {
    }

    /**
     *
     */
    private static class SingleSessionHelper
    {

        private static SessionFactory SESSION_FACTORY = null;

        private static SessionFactory build(Descriptor descriptor)
        {

            if (SESSION_FACTORY == null)
            {
                Configuration configuration = new Configuration();

                for (Map.Entry<String, String> entry : descriptor.getParams().entrySet())
                {
                    configuration.setProperty(entry.getKey(), entry.getValue());
                }

                for (Class annotatedClass : descriptor.getAnnotatedClasses())
                {
                    configuration.addAnnotatedClass(annotatedClass);
                }

                try
                {
                    SESSION_FACTORY = configuration.buildSessionFactory();
                } catch (HibernateException ex)
                {
                    ex.printStackTrace();
                }
            }
            return SESSION_FACTORY;
        }

        /**
         *
         * @return
         */
        private static SessionFactory getSession()
        {
            return SESSION_FACTORY;
        }
    }

    /**
     *
     * @param descriptor
     * @return
     */
    public static SessionFactory buildInstance(Descriptor descriptor)
    {
        return SingleSessionHelper.build(descriptor);
    }

    /**
     *
     * @return
     */
    public static SessionFactory getCurrentInstance()
    {
        return SingleSessionHelper.getSession();
    }

}
