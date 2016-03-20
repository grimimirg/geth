/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author agrimandi
 */
public class Descriptor
{

    private Map<String, String> params = new HashMap<>();
    private List<Class> annotatedClasses = new ArrayList<>();
    private List<String> modules = new ArrayList<>();
    private int socket = 60001;
    private boolean serverUp = false;

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
        return params;
    }

    /**
     *
     * @return
     */
    public List<Class> getAnnotatedClasses()
    {
        return annotatedClasses;
    }

    /**
     *
     * @param key
     * @return
     */
    public Object getParam(String key)
    {
        return params.get(key);
    }

    /**
     *
     * @param index
     * @return
     */
    public Class getAnnotatedClass(int index)
    {
        return annotatedClasses.get(index);
    }

    /**
     *
     * @return
     */
    public List<String> getModules()
    {
        return modules;
    }

    /**
     *
     * @param modules
     */
    public void setModules(List<String> modules)
    {
        this.modules = modules;
    }

    /**
     *
     * @param cls
     */
    public void addModule(String cls)
    {
        this.modules.add(cls);
    }

    /**
     *
     * @return
     */
    public int getSocket()
    {
        return socket;
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
        return serverUp;
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
