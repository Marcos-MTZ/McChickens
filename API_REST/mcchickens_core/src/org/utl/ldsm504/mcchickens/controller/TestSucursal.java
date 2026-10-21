/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.utl.ldsm504.mcchickens.controller;

/**
 *
 * @author uriel
 */
public class TestSucursal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ControllerSucursal cs = new ControllerSucursal();
        try {
            cs.deleteSucursal(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
