/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core;

import it.geth.core.config.SingleSessionFactory;
import it.geth.core.config.annotation.ConfigurationAdapter;
import it.geth.core.config.annotation.SessionFactoryBuilder;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import org.reflections.Reflections;

/**
 *
 * @author agrimandi
 */
public class AppDbContext {

    private AppDbContext() {
    }

    public static AppDbContext getInstance(String rootContext) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return AppDbContextHolder.buildContext(rootContext);
    }

    private static class AppDbContextHolder {

        private static AppDbContext buildContext(String rootContext) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            Reflections rootSCope = new Reflections(rootContext);
            Set<Class<?>> adapters = rootSCope.getTypesAnnotatedWith(ConfigurationAdapter.class);

            Iterator iAdapter = adapters.iterator();

            while (iAdapter.hasNext()) {
                Class databaseAdapterClass = (Class) iAdapter.next();
                Object configurationAdapter = databaseAdapterClass.newInstance();

                Method[] databaseAdapterMethods = databaseAdapterClass.getMethods();
                for (Method method : databaseAdapterMethods) {
                    if (method.isAnnotationPresent(SessionFactoryBuilder.class)) {
                        method.invoke(configurationAdapter, null);
                    }
                }
            }
            return null;
        }
    }
}
