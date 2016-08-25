/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import java.util.Collection;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;

/**
 *
 * @author agrimandi
 */
public class Cryterion
{

    public static Criterion idEq(Object value)
    {
        return Restrictions.idEq(value);
    }

    public static Criterion neOrIsNotNull(String propertyName, Object value)
    {
        return Restrictions.neOrIsNotNull(propertyName, value);
    }

    public static Criterion ilike(String propertyName, Object value)
    {
        return Restrictions.ilike(propertyName, value);
    }

    public static Criterion ilike(String propertyName, String value, MatchMode matchMode)
    {
        return Restrictions.ilike(propertyName, value, matchMode);
    }

    public static Criterion between(String propertyName, Object lo, Object hi)
    {
        return Restrictions.between(propertyName, lo, hi);
    }

    public static Criterion in(String propertyName, Object... values)
    {
        return Restrictions.in(propertyName, values);
    }

    public static Criterion in(String propertyName, Collection values)
    {
        return Restrictions.in(propertyName, values);
    }

    public static Criterion isNull(String propertyName)
    {
        return Restrictions.isNull(propertyName);
    }

    public static Criterion isNotNull(String propertyName)
    {
        return Restrictions.isNotNull(propertyName);
    }

    public static Criterion not(Criterion expression)
    {
        return Restrictions.not(expression);
    }

    public static Criterion sqlRestriction(String sql, Object[] values, Type[] types)
    {
        return Restrictions.sqlRestriction(sql, values, types);
    }

    public static Criterion sqlRestriction(String sql, Object value, Type type)
    {
        return Restrictions.sqlRestriction(sql, value, type);
    }

    public static Criterion sqlRestriction(String sql)
    {
        return Restrictions.sqlRestriction(sql);
    }

    public static Criterion allEq(Map<String, ?> propertyNameValues)
    {
        return Restrictions.allEq(propertyNameValues);
    }

    public static Criterion isEmpty(String propertyName)
    {
        return Restrictions.isEmpty(propertyName);
    }

    public static Criterion isNotEmpty(String propertyName)
    {
        return Restrictions.isNotEmpty(propertyName);
    }

    public static Criterion sizeEq(String propertyName, int size)
    {
        return Restrictions.sizeEq(propertyName, size);
    }

    public static Criterion sizeNe(String propertyName, int size)
    {
        return Restrictions.sizeNe(propertyName, size);
    }

    public static Criterion sizeGt(String propertyName, int size)
    {
        return Restrictions.sizeGt(propertyName, size);
    }

    public static Criterion sizeLt(String propertyName, int size)
    {
        return Restrictions.sizeLt(propertyName, size);
    }

    public static Criterion sizeGe(String propertyName, int size)
    {
        return Restrictions.sizeGe(propertyName, size);
    }

    public static Criterion sizeLe(String propertyName, int size)
    {
        return Restrictions.sizeLe(propertyName, size);
    }

    public static Criterion gt(String field, Object value)
    {
        return Restrictions.gt(field, value);
    }

    public static Criterion eq(String field, Object value)
    {
        return Restrictions.eq(field, value);
    }

    public static Criterion or(Criterion... predicate)
    {
        return Restrictions.or(predicate);
    }

    public static Criterion and(Criterion... predicate)
    {
        return Restrictions.and(predicate);
    }

}
