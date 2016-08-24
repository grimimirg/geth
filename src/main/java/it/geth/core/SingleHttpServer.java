package it.geth.core;

import it.geth.core.config.Descriptor;
import it.grimi.modularserver.core.ModularServer;

/**
 *
 * @author agrimandi
 */
public class SingleHttpServer
{

    /**
     *
     */
    private SingleHttpServer()
    {
    }

    private static class SingleHttpServerHelper
    {

        private static ModularServer MODULAR_SERVER = null;

        /**
         *
         * @param descriptor
         * @return
         */
        private static ModularServer build(Descriptor descriptor)
        {
            if (MODULAR_SERVER == null)
            {
                MODULAR_SERVER = new ModularServer(descriptor.getSocket());
                MODULAR_SERVER.setClassHandlers(descriptor.getClassHandlers());
                MODULAR_SERVER.setHandlers(descriptor.getHandlers());
                MODULAR_SERVER.build();

                return MODULAR_SERVER;
            }

            return MODULAR_SERVER;
        }

        /**
         *
         * @return
         */
        private static ModularServer getServer()
        {
            return MODULAR_SERVER;
        }
    }

    /**
     *
     * @param descriptor
     * @return
     */
    public static ModularServer buildInstance(Descriptor descriptor)
    {
        if (descriptor.isServerUp())
        {
            return SingleHttpServerHelper.build(descriptor).start();
        } else
        {
            return null;
        }
    }

    /**
     *
     * @return
     */
    public static ModularServer getCurrentInstance()
    {
        return SingleHttpServerHelper.getServer();
    }

}
