/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import it.geth.core.db.Operations;
import it.grimi.modularserver.core.ModuleUtilities;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agrimandi
 */
public class RestHandler extends ModuleUtilities implements HttpHandler
{

    private Object entity = null;

    /**
     *
     * @param entity
     */
    public RestHandler(Object entity)
    {
        this.entity = entity;

        try {
            SingleHttpServer.getCurrentInstance().addModule(this.getClass().getName());
        } catch (Exception ex) {
            System.out.printf("HTTP Server must be started in order to add new modules. See configurations in your MainAdapter class\n");
            Logger.getLogger(RestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @param exch
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exch) throws IOException
    {
        String json = null;
        if (this.entity instanceof Class) {
            json = new Operations().loadAll((Class) this.entity).toJson();
        } else {
            try {
                json = new Operations().loadWhere(this.entity).toJson();
            } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException ex) {
                Logger.getLogger(RestHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        exch.sendResponseHeaders(200, json.length());
        OutputStream os = exch.getResponseBody();
        os.write(json.getBytes());
        os.close();
    }

}
