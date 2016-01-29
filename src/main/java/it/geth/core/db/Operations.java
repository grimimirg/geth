/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import it.geth.core.SingleSessionFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
import javax.persistence.Column;
import org.hibernate.Query;
import org.hibernate.Session;
import org.reflections.Reflections;

/**
 *
 * @author agrimandi
 */
public class Operations implements OperationDao {

    @Override
    public boolean saveToDb(Object toSave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Outcome loadFromDb(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Session session = SingleSessionFactory.getInstance().getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from " + toLoad.getClass().getSimpleName());

        Reflections reflections = new Reflections();
        Set<Method> annotatedMethods = reflections.getMethodsAnnotatedWith(Column.class);

        Iterator<Method> iteratorAnnotatedMethods = annotatedMethods.iterator();

        while (iteratorAnnotatedMethods.hasNext()) {
            Method currentMethod = iteratorAnnotatedMethods.next();
            Column values = currentMethod.getAnnotation(Column.class);

            query.setParameter(values.name(), currentMethod.invoke(toLoad, null));
        }

        return new Outcome(query.list());
    }

}
