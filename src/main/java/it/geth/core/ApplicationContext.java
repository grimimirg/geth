/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core;

import it.geth.core.config.annotation.ConfigurationAdapter;
import it.geth.core.config.annotation.SessionFactoryBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

/**
 *
 * @author agrimandi
 */
public class ApplicationContext
{

    final static Logger logger = null;

    private static ApplicationContext appContext = null;

    private static String rootContext = null;

    protected ApplicationContext(String rootContext)
    {
        ApplicationContext.rootContext = rootContext;

        Reflections rootScope = new Reflections(ApplicationContext.rootContext);
        Set<Class<?>> adapters = rootScope.getTypesAnnotatedWith(ConfigurationAdapter.class);

        Iterator iAdapter = adapters.iterator();

        while (iAdapter.hasNext())
        {

            try
            {
                Class databaseAdapterClass = (Class) iAdapter.next();
                Object configurationAdapter = databaseAdapterClass.newInstance();
                Method[] databaseAdapterMethods = databaseAdapterClass.getMethods();

                for (Method method : databaseAdapterMethods)
                {
                    if (method.isAnnotationPresent(SessionFactoryBuilder.class))
                    {
                        method.invoke(configurationAdapter, null);
                    }
                }

            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
            {
                //Logger.getLogger(ApplicationContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void startServer()
    {

    }

    public Logger getLogger()
    {
        return null;
    }

    public static String getRootContext()
    {
        return ApplicationContext.rootContext;
    }

    public static ApplicationContext getInstance()
    {
        return appContext;
    }

    public static ApplicationContext buildContext(String rootContext)
    {
        if (appContext == null)
        {
            appContext = new ApplicationContext(rootContext);
        }
        return appContext;
    }

}
