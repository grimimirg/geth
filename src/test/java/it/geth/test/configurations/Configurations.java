/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.test.configurations;

import it.geth.test.model.User;
import it.geth.core.MainAdapter;
import it.geth.core.config.Descriptor;
import it.geth.core.config.annotation.ConfigurationAdapter;
import it.geth.test.model.Stuff;

/**
 *
 * @author agrimandi
 */
@ConfigurationAdapter
public class Configurations extends MainAdapter
{

    @Override
    public void configureDatabaseAdapter(Descriptor descriptor)
    {
        descriptor.addProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        descriptor.addProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        descriptor.addProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/postgres");
        descriptor.addProperty("hibernate.connection.username", "postgres");
        descriptor.addProperty("hibernate.connection.password", "tette123");
        descriptor.addProperty("hibernate.current_session_context_class", "thread");
        descriptor.addProperty("show_sql", "true");
        descriptor.addProperty("format_sql", "true");

        descriptor.addAnnotatedClass(Stuff.class);
        descriptor.addAnnotatedClass(User.class);

        /*descriptor.setServerUp(true);
        descriptor.setSocket(60001);*/
    }

}
