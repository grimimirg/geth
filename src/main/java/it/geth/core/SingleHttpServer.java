package it.geth.core;

import it.geth.core.config.Descriptor;
import it.grimi.modularserver.core.ModularServer;

public class SingleHttpServer
{

    private SingleHttpServer()
    {
    }

    private static class SingleHttpServerHelper
    {

        private static ModularServer MODULAR_SERVER = null;

        private static ModularServer build(Descriptor descriptor)
        {
            if (MODULAR_SERVER == null) {
                MODULAR_SERVER = new ModularServer(descriptor.getSocket());
                MODULAR_SERVER.setModules(descriptor.getModules());

                return MODULAR_SERVER;
            }
            return MODULAR_SERVER;
        }

        private static ModularServer getSession()
        {
            return MODULAR_SERVER;
        }
    }

    public static ModularServer buildInstance(Descriptor descriptor)
    {
        if (descriptor.isServerUp()) {
            return SingleHttpServerHelper.build(descriptor);
        } else {
            return null;
        }
    }

    public static ModularServer getCurrentInstance()
    {
        return SingleHttpServerHelper.getSession();
    }

}
