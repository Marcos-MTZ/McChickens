/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm504.mcchickens.model;

/**
 *
 * @author uriel
 */
public class Empleado {
    private int idEmpleado, estatus;
    private Sucursal sucursal;
    private String usuario, contrasenia, telefono;
    private Persona persona;
    
    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getEstatus() {
        return estatus;
    }

    public void setEstatus(int estatus) {
        this.estatus = estatus;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Empleado() {
    }

    public Empleado(int idEmpleado, String usuario, String contrasenia, String telefono, Sucursal sucursal, int estatus, Persona persona) {
        this.idEmpleado = idEmpleado;
        this.sucursal = sucursal;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.estatus = estatus;
        this.persona = persona;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + 
                " estatus: "+ estatus + 
                " usuario=" + usuario + 
                " contrasenia=" + contrasenia + 
                " telefono=" + telefono + 
                " sucursal=" + sucursal.getDomicilio() + 
                " Persona:"+persona.getNombre() +" "+persona.getApellido()+'}';
    }  
}