/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.test;

import it.geth.test.model.User;
import it.geth.core.ApplicationContext;
import it.geth.core.RestHandler;
import it.geth.core.SingleHttpServer;
import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author agrimandi
 */
public class MainTest
{

    /**
     * @param args the command line arguments
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws java.lang.reflect.InvocationTargetException
     */
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
    {
        ApplicationContext.buildContext("it.geth.test");

        User user = new User();
        user.setUsername("ffrosky");
//        String json = new Operations().loadWhere(user).toJson();
//        System.out.println(json);
        SingleHttpServer.getCurrentInstance().addModule(new RestHandler(user));

//        User toInsert = new User();
//        toInsert.setName("pipitou");
//        toInsert.setSurname("ciffoli");
//        toInsert.setUsername("pciffoli");
//        toInsert.setPassword("123456");
//
//        if (new Operations().save(toInsert))
//        {
//            System.out.println("Ok");
//        } else
//        {
//            System.out.println("Nok");
//        }
    }
}
