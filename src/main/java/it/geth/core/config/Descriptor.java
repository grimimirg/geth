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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author agrimandi
 */
public class Descriptor {

    private Map<String, String> params = new HashMap<>();
    private List<Class> annotatedClasses = new ArrayList<>();
    private List<Class> modules = new ArrayList<>();

    /**
     *
     */
    public Descriptor() {
    }

    /**
     *
     * @param params
     * @param annotatedClasses
     */
    public Descriptor(Map<String, String> params, List<Class> annotatedClasses) {
        this.params = params;
        this.annotatedClasses = annotatedClasses;
    }

    /**
     *
     * @param key
     * @param value
     */
    public void addProperty(String key, String value) {
        this.params.put(key, value);
    }

    /**
     *
     * @param cls
     */
    public void addAnnotatedClass(Class cls) {
        this.annotatedClasses.add(cls);
    }

    /**
     *
     * @return
     */
    public Map<String, String> getParams() {
        return params;
    }

    /**
     *
     * @return
     */
    public List<Class> getAnnotatedClasses() {
        return annotatedClasses;
    }

    /**
     *
     * @param key
     * @return
     */
    public Object getParam(String key) {
        return params.get(key);
    }

    /**
     *
     * @param index
     * @return
     */
    public Class getAnnotatedClass(int index) {
        return annotatedClasses.get(index);
    }

    /**
     *
     * @return
     */
    public List<Class> getModules() {
        return modules;
    }

    /**
     *
     * @param modules
     */
    public void setModules(List<Class> modules) {
        this.modules = modules;
    }

    /**
     * 
     * @param cls 
     */
    public void addModule(Class cls) {
        this.modules.add(cls);
    }

    /**
     * 
     * @param cls 
     */
    public void addModule(String cls) {
        try {
            this.modules.add(Class.forName(cls));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Descriptor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
