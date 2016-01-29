/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.List;

/**
 *
 * @author agrimandi
 */
public class Outcome {

    private List result;

    public Outcome() {

    }

    public void setResult(List result) {
        this.result = result;
    }

    public Outcome(List result) {
        this.result = result;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this.result);
    }

    public List toList() {
        return this.result;
    }

}
