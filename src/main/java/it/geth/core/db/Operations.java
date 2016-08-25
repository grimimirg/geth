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
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author agrimandi
 */
public class Operations
{

    /**
     *
     */
    private final Session session = SingleSessionFactory.buildInstance(null).getCurrentSession();

    /**
     * Save an object into the database using the <b>toSave.getClass()</b> as
     * the table name.
     *
     * NB: <b>toSave</b> object must be annotated with the
     * <b>javax.persistence</b> annotation framework.
     *
     * @param toSave
     * @return
     */
    public boolean save(Object toSave) throws HibernateException
    {
        try
        {
            this.session.beginTransaction();
            this.session.save(toSave);
            this.session.getTransaction().commit();
            return true;
        } catch (HibernateException ex)
        {
            return false;
        }
    }

    /**
     * Perform a select from the database using the <b>toLoad getter methods</b>
     * as the where conditions. For loading by id use the <b>loadWhereId()</b>
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
    public Outcome loadWhere(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException
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
     * as the where conditions. For loading by other fields use
     * <b>loadWhere()</b>
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
    public Outcome loadWhereId(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException
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

    /**
     *
     * @param toLoad
     * @return
     */
    public Outcome loadAll(Class toLoad)
    {
        return new Outcome(this.session.createQuery("from " + toLoad.getName()).list());
    }

    /**
     *
     * @param toLoad
     * @return
     */
    public Cryteria loadFromDb(Class toLoad)
    {
        return new Cryteria(this.session.createCriteria(toLoad));
    }

}
