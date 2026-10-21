/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm504.mcchickens.model;

/**
 *
 * @author uriel
 */
public class Sucursal {
    private String domicilio, plaza;
    private int idSucursal, estatus;
    private double latitud, longitud;

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public Sucursal() {
    }

    public Sucursal(String domicilio, String plaza, int idSucursal, int estatus, double latitud, double longitud) {
        this.domicilio = domicilio;
        this.plaza = plaza;
        this.idSucursal = idSucursal;
        this.estatus = estatus;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "-------" + "\n"+
                "Plaza:" + plaza + "\n"+ 
                "ID: " + idSucursal + "\n"+
                "estatus: " + estatus + "\n"+
                "Domicilio: " + domicilio + "\n"+
                "Latitud: " + latitud + "\n"+
                "Longitud: " + longitud;
    }
    
    
}
