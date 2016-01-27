/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.test;

import it.geth.core.DatabaseAdapter;
import it.geth.core.config.Descriptor;
import it.geth.core.config.annotation.BasePackage;
import it.geth.core.config.annotation.ConfigurationAdapter;

/**
 *
 * @author agrimandi
 */
@ConfigurationAdapter
@BasePackage(basePackage = "it.geth.test")
public class Configurations extends DatabaseAdapter {

    @Override
    public void configureDatabaseAdapter(Descriptor descriptor) {
        descriptor.addProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        descriptor.addProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        descriptor.addProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:5432/postgres");
        descriptor.addProperty("hibernate.connection.username", "postgres");
        descriptor.addProperty("hibernate.connection.password", "tette123");
        descriptor.addProperty("hibernate.current_session_context_class", "thread");

        descriptor.addAnnotatedClass(Entity.class);
    }

}
