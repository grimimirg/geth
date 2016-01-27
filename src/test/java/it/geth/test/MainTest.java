/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.test;

import it.geth.core.config.Descriptor;
import it.geth.core.config.SingleSessionFactory;
import org.hibernate.SessionFactory;

/**
 *
 * @author agrimandi
 */
public class MainTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Descriptor descriptor = new Descriptor();
        descriptor.addProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        descriptor.addProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        descriptor.addProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/postgres");
        descriptor.addProperty("hibernate.connection.username", "postgres");
        descriptor.addProperty("hibernate.connection.password", "tette123");
        descriptor.addProperty("hibernate.current_session_context_class", "thread");

        descriptor.addAnnotatedClass(Entity.class);

        SessionFactory singleSessionFactory = SingleSessionFactory.getInstance(descriptor);
        SessionFactory singleSessionFactory2 = SingleSessionFactory.getInstance();
        System.out.println("halla halla");
    }

}
