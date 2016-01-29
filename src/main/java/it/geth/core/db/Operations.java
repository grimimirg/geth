/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import it.geth.core.SingleSessionFactory;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.persistence.Column;
import javax.persistence.Id;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author agrimandi
 */
public class Operations implements OperationDao {

    @Override
    public boolean save(Object toSave) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Outcome load(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Session session = SingleSessionFactory.getInstance().getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(toLoad.getClass());

        Method[] methods = toLoad.getClass().getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Column.class) && !method.isAnnotationPresent(Id.class)) {
                Column column = method.getAnnotation(Column.class);
                Object value = method.invoke(toLoad, null);
                if (value != null) {
                    criteria.add(Restrictions.eq(column.name(), value));
                }
            }
        }

        return new Outcome(criteria.list());
    }

    @Override
    public Outcome loadById(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException {
        Session session = SingleSessionFactory.getInstance().getCurrentSession();
        session.beginTransaction();

        Criteria criteria = session.createCriteria(toLoad.getClass());

        Method[] methods = toLoad.getClass().getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Id.class)) {
                Column column = method.getAnnotation(Column.class);
                Object value = method.invoke(toLoad, null);
                if (value != null) {
                    criteria.add(Restrictions.eq(column.name(), value));
                }
            }
        }

        return new Outcome(criteria.list());
    }

}
