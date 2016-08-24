/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.config;

import com.sun.net.httpserver.HttpHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper for Geth application parameters.
 *
 * @author agrimandi
 */
public class Descriptor
{

    private Map<String, String> params = new HashMap<>();
    private List<Class> annotatedClasses = new ArrayList<>();

    private int socket = 59997;
    private boolean serverUp = false;

    private List<String> classHandlers = new ArrayList<>();
    private List<HttpHandler> handlers = new ArrayList<>();

    /**
     *
     */
    public Descriptor()
    {
    }

    /**
     *
     * @param params
     * @param annotatedClasses
     */
    public Descriptor(Map<String, String> params, List<Class> annotatedClasses)
    {
        this.params = params;
        this.annotatedClasses = annotatedClasses;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void addProperty(String key, String value)
    {
        this.params.put(key, value);
    }

    /**
     *
     * @param cls
     */
    public void addAnnotatedClass(Class cls)
    {
        this.annotatedClasses.add(cls);
    }

    /**
     *
     * @return
     */
    public Map<String, String> getParams()
    {
        return this.params;
    }

    /**
     *
     * @return
     */
    public List<Class> getAnnotatedClasses()
    {
        return this.annotatedClasses;
    }

    /**
     *
     * @param key
     * @return
     */
    public Object getParam(String key)
    {
        return this.params.get(key);
    }

    /**
     *
     * @param index
     * @return
     */
    public Class getAnnotatedClass(int index)
    {
        return this.annotatedClasses.get(index);
    }

    /**
     *
     * @return
     */
    public List<String> getClassHandlers()
    {
        return this.classHandlers;
    }

    /**
     *
     * @return
     */
    public List<HttpHandler> getHandlers()
    {
        return this.handlers;
    }

    /**
     *
     * @param classHandlers
     */
    public void setClassHandlers(List<String> classHandlers)
    {
        this.classHandlers = classHandlers;
    }

    /**
     *
     * @param cls
     */
    public void addModule(String cls)
    {
        this.classHandlers.add(cls);
    }

    /**
     *
     * @param handlers
     */
    public void setHandlers(List<HttpHandler> handlers)
    {
        this.handlers = handlers;
    }

    /**
     *
     * @param handler
     */
    public void addModule(HttpHandler handler)
    {
        this.handlers.add(handler);
    }

    /**
     *
     * @return
     */
    public int getSocket()
    {
        return this.socket;
    }

    /**
     *
     * @param socket
     */
    public void setSocket(int socket)
    {
        this.socket = socket;
    }

    /**
     *
     * @return
     */
    public boolean isServerUp()
    {
        return this.serverUp;
    }

    /**
     *
     * @param serverUp
     */
    public void setServerUp(boolean serverUp)
    {
        this.serverUp = serverUp;
    }

}
