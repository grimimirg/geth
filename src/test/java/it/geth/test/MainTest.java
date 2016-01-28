/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.test;

import it.geth.core.AppDbContext;
import it.geth.core.config.SingleSessionFactory;
import java.lang.reflect.InvocationTargetException;
import org.hibernate.SessionFactory;

/**
 *
 * @author agrimandi
 */
public class MainTest {

    /**
     * @param args the command line arguments
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        AppDbContext app = AppDbContext.getInstance("it.geth.test");

        SessionFactory sf = SingleSessionFactory.getInstance();

    }

}
