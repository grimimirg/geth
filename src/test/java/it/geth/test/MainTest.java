/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.test;

import it.geth.core.ApplicationContext;
import it.geth.core.db.OperationDao;
import it.geth.core.db.Operations;
import java.lang.reflect.InvocationTargetException;

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
        ApplicationContext app = ApplicationContext.getInstance("it.geth.test");
        User user = new User();
        user.setId(1);
        String json = new Operations().loadFromDb(user).toJson();
    }

}
