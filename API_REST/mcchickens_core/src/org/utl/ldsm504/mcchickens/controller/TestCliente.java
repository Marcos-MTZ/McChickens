/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.utl.ldsm504.mcchickens.controller;

import java.util.List;
import org.utl.ldsm504.mcchickens.model.Cliente;
import org.utl.ldsm504.mcchickens.model.Persona;
import org.utl.ldsm504.mcchickens.model.Sucursal;

/**
 *
 * @author uriel
 */
public class TestCliente {

    public static void insertCliente() {
        ControllerCliente cc = new ControllerCliente();
        Persona persona = new Persona();
        Cliente cliente = new Cliente();
        Sucursal sucursal = new Sucursal();
        persona.setNombre("Miguel");
        persona.setApellido("Aguirre");
        cliente.setCorreo("migue@gmail.com");
        sucursal.setIdSucursal(3);

        cliente.setPersona(persona);
        cliente.setSucursal(sucursal);

        cc.agregarCliente(cliente);
    }

    public static void main(String[] args) {
        ControllerCliente cc = new ControllerCliente();

        insertCliente();
        System.out.println("\n-- Lista de Clientes --");
        List<Cliente> clientes = cc.getAll();
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }
}
