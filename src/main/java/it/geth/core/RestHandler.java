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
import java.lang.reflect.Method;
import java.util.Map;
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
     */
    public RestHandler()
    {

    }

    /**
     *
     * @param entity
     */
    public RestHandler expose(Object entity)
    {
        this.entity = entity;
        return this;
    }

    /**
     *
     * @param exch
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange exch) throws IOException
    {
        String response = null;
        int httpCode = 200;

        if(this.entity != null)
        {
            if (this.entity instanceof Class)
            {
                Map<String, String> reqParams = this.getParams(exch.getRequestURI().getQuery());
                if (reqParams.size() == 0)
                {
                    response = new Operations().loadAll((Class) this.entity).toJson();
                } else
                {
                    try
                    {
                        Object obj = this.entity.getClass().newInstance();

                        for (Map.Entry<String, String> entry : reqParams.entrySet())
                        {
                            for (Method method : this.entity.getClass().getDeclaredMethods())
                            {
                                if (method.getName().toLowerCase().equals("set" + entry.getKey()))
                                {
                                    method.invoke(null, entry.getValue());
                                }
                            }
                        }

                        response = new Operations().loadWhere(obj).toJson();
                    } catch (Exception ex)
                    {
                        Logger.getLogger(RestHandler.class.getName()).log(Level.SEVERE, null, ex);
                        response = ex.getMessage();
                        httpCode = 500;
                    }
                }

            } else
            {
                try
                {
                    response = new Operations().loadWhere(this.entity).toJson();
                } catch (IllegalArgumentException | InvocationTargetException | IllegalAccessException ex)
                {
                    Logger.getLogger(RestHandler.class.getName()).log(Level.SEVERE, null, ex);
                    response = ex.getMessage();
                    httpCode = 500;
                }
            }
        } else
        {
            response = "";
            httpCode = 500;
        }

        exch.sendResponseHeaders(httpCode, response.length());
        OutputStream outputStream = exch.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
