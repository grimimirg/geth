/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import java.util.List;

/**
 *
 * @author agrimandi
 */
public class Outcome {

    private List result;

    public Outcome() {

    }

    public Outcome(List result) {
        this.result = result;
    }

    public String toJson() {
        //convert list to json
        return null;
    }

    public String toXml() {
        //convert list to json
        return null;
    }

    public List toList() {
        return this.result;
    }

}
