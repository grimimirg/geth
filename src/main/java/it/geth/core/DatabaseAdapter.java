/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core;

import it.geth.core.config.Descriptor;
import it.geth.core.config.SingleSessionFactory;
import it.geth.core.config.annotation.SessionFactoryBuilder;

/**
 *
 * @author agrimandi
 */
public abstract class DatabaseAdapter {

    private Descriptor descriptor = new Descriptor();

    /**
     *
     * @param descriptor
     */
    public void configureDatabaseAdapter(Descriptor descriptor) {

    }

    /**
     *
     */
    @SessionFactoryBuilder
    public void buildSessionFactory() {
        this.configureDatabaseAdapter(this.descriptor);
        SingleSessionFactory.getInstance(this.descriptor);
    }
}
