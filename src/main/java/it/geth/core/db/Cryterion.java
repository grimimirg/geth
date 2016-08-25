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

}
