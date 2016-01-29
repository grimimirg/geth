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
import org.reflections.Reflections;

/**
 *
 * @author agrimandi
 */
public class ApplicationContext {

    private ApplicationContext() {
    }

    public static ApplicationContext getInstance(String rootContext) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return AppDbContextHolder.buildContext(rootContext);
    }

    private static class AppDbContextHolder {

        private static ApplicationContext buildContext(String rootContext) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
            buildSingleSessionFactory(rootContext);
            return null;
        }

        private static void buildSingleSessionFactory(String rootContext) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException {
            Reflections rootScope = new Reflections(rootContext);
            Set<Class<?>> adapters = rootScope.getTypesAnnotatedWith(ConfigurationAdapter.class);

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
        }
    }
}
