/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm504.mcchickens.model;

/**
 *
 * @author uriel
 */
public class Cliente {
    private int idCliente, estatus;
    private String correo, codigoCliente;
    private Sucursal sucursal;
    private Persona persona;

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Cliente(int idCliente, int estatus, String correo, String codigoCliente, Sucursal sucursal, Persona persona) {
        this.idCliente = idCliente;
        this.estatus = estatus;
        this.correo = correo;
        this.codigoCliente = codigoCliente;
        this.sucursal = sucursal;
        this.persona = persona;
    }

    public Cliente() {
    }

    @Override
    public String toString() {
        return " Cliente{" + "idCliente=" + idCliente + 
               " estatus=" + estatus + 
               " correo=" + correo + 
               " codigoCliente=" + codigoCliente + 
               " sucursal=" + sucursal.getDomicilio() + 
               " persona=" + persona.getNombre() + " " + persona.getApellido() + '}';
    }
}
