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
import java.util.logging.Logger;
import org.reflections.Reflections;

/**
 *
 * @author agrimandi
 */
public class ApplicationContext {

    private static ApplicationContext appContext = null;

    private static String rootContext = null;

    protected ApplicationContext(String rootContext) {
        ApplicationContext.rootContext = rootContext;

        Reflections rootScope = new Reflections(ApplicationContext.rootContext);
        Set<Class<?>> adapters = rootScope.getTypesAnnotatedWith(ConfigurationAdapter.class);

        Iterator iAdapter = adapters.iterator();

        while (iAdapter.hasNext()) {
            try {
                Class databaseAdapterClass = (Class) iAdapter.next();
                Object configurationAdapter = databaseAdapterClass.newInstance();

                Method[] databaseAdapterMethods = databaseAdapterClass.getMethods();
                for (Method method : databaseAdapterMethods) {
                    if (method.isAnnotationPresent(SessionFactoryBuilder.class)) {
                        method.invoke(configurationAdapter, null);
                    }
                }
            } catch (InstantiationException ex) {
                Logger.getLogger(ApplicationContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(ApplicationContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(ApplicationContext.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvocationTargetException ex) {
                Logger.getLogger(ApplicationContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static String rootContext() {
        return ApplicationContext.rootContext;
    }

    public static ApplicationContext getInstance() {
        return appContext;
    }

    public static ApplicationContext getInstance(String rootContext) {
        if (appContext == null) {
            appContext = new ApplicationContext(rootContext);
        }
        return appContext;
    }

}
