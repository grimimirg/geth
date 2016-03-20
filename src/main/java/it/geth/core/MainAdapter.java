/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core;

import it.geth.core.config.Descriptor;
import it.geth.core.config.annotation.Configurations;

/**
 *
 * @author agrimandi
 */
public abstract class MainAdapter
{

    private final Descriptor descriptor = new Descriptor();

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
        SingleSessionFactory.buildInstance(this.descriptor);
    }

    /**
     *
     */
    private void httpServer()
    {
        SingleHttpServer.buildInstance(this.descriptor);
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
