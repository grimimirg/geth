/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geth.core;

/**
 *
 * @author agrimandi
 */
public class AppDbContext {

    private AppDbContext() {
    }

    public static AppDbContext getInstance() {
        return AppDbContextHolder.INSTANCE;
    }

    private static class AppDbContextHolder {

        private static final AppDbContext INSTANCE = buildContext();

        private static AppDbContext buildContext() {
            
            return null;
        }
    }
}
