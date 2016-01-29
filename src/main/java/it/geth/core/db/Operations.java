/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import it.geth.core.SingleSessionFactory;
import org.hibernate.Session;

/**
 *
 * @author agrimandi
 */
public class Operations implements OperationDao {

    public boolean saveToDb() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Outcome loadFromDb(Object toLoad) {
        Session session = SingleSessionFactory.getInstance().getCurrentSession();
        session.beginTransaction();
        return new Outcome(session.createQuery("from " + toLoad.getClass().getSimpleName()).list());
    }

}
