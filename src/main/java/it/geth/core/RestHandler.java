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

/**
 *
 * @author agrimandi
 */
public class RestHandler extends ModuleUtilities implements HttpHandler
{

    private Class entity = null;

    public RestHandler(Class entity)
    {
        this.entity = entity;
        SingleHttpServer.getCurrentInstance().addModule(this.getClass().getName());
    }

    @Override
    public void handle(HttpExchange exch) throws IOException
    {
        String json = new Operations().loadAll(this.entity).toJson();
        exch.sendResponseHeaders(200, json.length());
        OutputStream os = exch.getResponseBody();
        os.write(json.getBytes());
        os.close();
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
