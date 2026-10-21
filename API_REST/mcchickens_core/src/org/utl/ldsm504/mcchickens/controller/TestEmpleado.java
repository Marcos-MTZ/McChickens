/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.utl.ldsm504.mcchickens.controller;

import java.util.List;
import org.utl.ldsm504.mcchickens.model.Empleado;
import org.utl.ldsm504.mcchickens.model.Persona;
import org.utl.ldsm504.mcchickens.model.Sucursal;

/**
 *
 * @author uriel
 */
public class TestEmpleado {
    
    public static void insertEmpleado(){
        ControllerEmpleado ce = new ControllerEmpleado();
        Persona persona = new Persona();
        Empleado empleado = new Empleado();
        Sucursal sucursal =  new Sucursal();
        
        persona.setNombre("Natalia");
        persona.setApellido("Orozco");
        empleado.setTelefono("4772154826");
        empleado.setContrasenia("123s6d8a4");
        sucursal.setIdSucursal(2);
        
        empleado.setPersona(persona);
        empleado.setSucursal(sucursal);
        
        ce.agregarEmpleado(empleado);
    }

    public static void main(String[] args) {
        ControllerEmpleado ce = new ControllerEmpleado();
        
        insertEmpleado();

        System.out.println("\n-- Lista de Empleados --");
        List<Empleado> empleados = ce.getAll();
        for (Empleado e : empleados) {
            System.out.println(""+e);
        }
    }
    
}
