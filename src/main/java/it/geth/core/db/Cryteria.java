/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author agrimandi
 */
public class Cryteria
{

    private final Criteria criteria;

    public Cryteria(Criteria criteria)
    {
        this.criteria = criteria;
    }

    /**
     *
     * @param predicate
     * @return
     */
    public Cryteria addCriteria(Criterion... predicate)
    {
        for (Criterion criteria : predicate)
        {
            this.criteria.add(Restrictions.and(criteria));
        }

        return this;
    }

    /**
     * Executes the created query.
     *
     * @return
     */
    public Outcome execute()
    {
        return new Outcome(this.criteria.list());
    }
}
