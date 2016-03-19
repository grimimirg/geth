/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core;

import it.geth.core.config.Descriptor;
import it.geth.core.config.annotation.Configurations;
import it.grimi.modularserver.core.ModularServer;

/**
 *
 * @author agrimandi
 */
public abstract class MainAdapter
{

    private Descriptor descriptor = new Descriptor();

    /**
     *
     * @param descriptor
     */
    public void configureDatabaseAdapter(Descriptor descriptor)
    {

    }

    /**
     *
     */
    private void sessionFactory()
    {
        SingleSessionFactory.getInstance(this.descriptor);
    }

    /**
     *
     */
    private void httpServer()
    {
        new ModularServer(this.descriptor.getSocket()).start(this.descriptor.getModules());
    }

    /**
     *
     */
    @Configurations
    public void sessionFactoryBuilder()
    {
        this.configureDatabaseAdapter(this.descriptor);
        this.sessionFactory();
        this.httpServer();
    }
}
