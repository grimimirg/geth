/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author agrimandi
 */
public class Cryterion
{

    /**
     *
     * @param field
     * @param value
     * @return
     */
    public static Criterion gt(String field, Object value)
    {
        return Restrictions.gt(field, value);
    }

    /**
     *
     * @param field
     * @param value
     * @return
     */
    public static Criterion eq(String field, Object value)
    {
        return Restrictions.eq(field, value);
    }

    /**
     *
     * @param predicate
     * @return
     */
    public static Criterion or(Criterion... predicate)
    {
        return Restrictions.or(predicate);
    }

    /**
     *
     * @param predicate
     * @return
     */
    public static Criterion and(Criterion... predicate)
    {
        return Restrictions.and(predicate);
    }

}
