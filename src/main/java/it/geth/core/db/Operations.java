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
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author agrimandi
 */
public class Operations implements OperationDao
{

    private final DelegateSession session = new DelegateSession(SingleSessionFactory.getInstance().getCurrentSession());

    /**
     * Save an object into the database using the <b>toSave.getClass()</b> as
     * the table name.
     *
     * NB: <b>toSave</b> object must be annotated with the
     * <b>javax.persistence</b> annotation framework.
     *
     * @param toSave
     */
    @Override
    public void save(Object toSave) throws HibernateException
    {
        this.session.beginTransaction();
        this.session.save(toSave);
        this.session.getTransaction().commit();
    }

    /**
     * Perform a select from the database using the <b>toLoad getter methods</b>
     * as the where conditions. For loading by id use the <b>loadById()</b>
     * function instead.
     *
     * NB: <b>toLoad</b> must be annotated with <b>javax.persistence</b>
     * annotation framework.
     *
     * @param toLoad
     * @return
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public Outcome load(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException
    {
        this.session.beginTransaction();

        Criteria criteria = this.session.createCriteria(toLoad.getClass());

        Method[] methods = toLoad.getClass().getMethods();

        for (Method method : methods)
        {
            if (method.isAnnotationPresent(Column.class) && !method.isAnnotationPresent(Id.class))
            {
                Column column = method.getAnnotation(Column.class);
                Object value = method.invoke(toLoad, (Object[]) null);
                if (value != null)
                {
                    criteria.add(Restrictions.eq(column.name(), value));
                }
            }
        }

        return new Outcome(criteria.list());
    }

    /**
     * Perform a select from the database using the <b>toLoad id getter</b>
     * as the where conditions. For loading by other fields use <b>load()</b>
     * function instead.
     *
     * NB: <b>toLoad</b> must be annotated with the <b>javax.persistence</b>
     * annotation framework.
     *
     * @param toLoad
     * @return
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public Outcome loadById(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException
    {
        this.session.beginTransaction();

        Criteria criteria = this.session.createCriteria(toLoad.getClass());

        Method[] methods = toLoad.getClass().getMethods();

        for (Method method : methods)
        {
            if (method.isAnnotationPresent(Id.class))
            {
                Column column = method.getAnnotation(Column.class);
                Object value = method.invoke(toLoad, (Object[]) null);
                if (value != null)
                {
                    criteria.add(Restrictions.eq(column.name(), value));
                }
            }
        }

        return new Outcome(criteria.list());
    }

}
