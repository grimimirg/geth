/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 *
 * @author agrimandi
 */
public interface OperationDao {

    public boolean save(Object toSave);

    public Outcome load(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException;
    
    public Outcome loadById(Object toLoad) throws IllegalArgumentException, InvocationTargetException, IllegalAccessException;

}
