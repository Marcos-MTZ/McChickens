/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm504.mcchickens.controller;

import org.utl.ldsm504.mcchickens.bd.ConexionMySQL;
import org.utl.ldsm504.mcchickens.model.Cliente;
import java.sql.CallableStatement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import org.utl.ldsm504.mcchickens.model.Persona;
import org.utl.ldsm504.mcchickens.model.Sucursal;
/**
 *
 * @author uriel
 */
public class ControllerCliente {
    // Crear el getAll de cliente tarea: 1 Hacer la vista en mysql de cliente 2. Hacer la funcion
     
    public Cliente agregarCliente(Cliente c){
        ConexionMySQL conexion = new ConexionMySQL(); //conexion a la bd
        Connection con = null;
        CallableStatement clst = null;
        int idPersona=0,idCliente=0;
        String codigoC = "";
        String query = "{CALL sp_insert_cliente(?,?,?,?,?,?,?)}";
        try {
            con = conexion.open();
            clst = con.prepareCall(query);
            clst.setString(1, c.getPersona().getNombre());
            clst.setString(2, c.getPersona().getApellido());
            clst.setString(3, c.getCorreo());
            clst.setInt(4, c.getSucursal().getIdSucursal());
            clst.registerOutParameter(5, Types.INTEGER);
            clst.registerOutParameter(6, Types.INTEGER);
            clst.registerOutParameter(7, Types.VARCHAR);
            clst.execute();
            
            idPersona = clst.getInt(5);
            idCliente = clst.getInt(6);
            codigoC = clst.getString(7);
            c.setIdCliente(idCliente);
            c.getPersona().setIdPersona(idPersona);
            c.setCodigoCliente(codigoC);
            c.setEstatus(1);
            
            clst.close();
            con.close();
            conexion.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
    
     public void deleteCliente(int id){
        ConexionMySQL conexion = new ConexionMySQL();
        Connection con = null;
        CallableStatement clst = null;
        String query = "{CALL sp_delete_cliente(?)}";
        try {
            con = conexion.open();
            clst = con.prepareCall(query);
            clst.setInt(1, id);
            clst.execute();
            
            clst.close();
            conexion.close();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateCliente(Cliente c){
        ConexionMySQL conexion = new ConexionMySQL();
        Connection con = null;
        String query = "CALL sp_update_cliente(?,?,?,?,?)";
        CallableStatement clst = null;
        try {
            con = conexion.open();
            clst = con.prepareCall(query);
            clst.setInt(1, c.getIdCliente());
            clst.setInt(2, c.getSucursal().getIdSucursal());
            clst.setString(3, c.getCorreo());
            clst.setString(4, c.getPersona().getNombre());
            clst.setString(5, c.getPersona().getApellido());
            
            clst.execute();
            
            clst.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<Cliente> getAll(){
    List<Cliente> clientes = new ArrayList<>();
    String query = "SELECT * FROM v_clientes";
    ConexionMySQL con = new ConexionMySQL();
    try {
        Connection conexion = con.open();
        PreparedStatement psmt = conexion.prepareStatement(query);
        ResultSet rs = psmt.executeQuery();
        while(rs.next()){
            Sucursal s = new Sucursal();
            Persona p = new Persona();
            Cliente c = new Cliente();

            s.setIdSucursal(rs.getInt("id_sucursal"));
            s.setPlaza(rs.getString("plaza"));
            s.setDomicilio(rs.getString("domicilio"));

            p.setIdPersona(rs.getInt("id_persona"));
            p.setNombre(rs.getString("nombre"));
            p.setApellido(rs.getString("apellido"));

            c.setIdCliente(rs.getInt("id_cliente"));
            c.setCorreo(rs.getString("email"));
            c.setCodigoCliente(rs.getString("codigo_cliente"));
            c.setEstatus(rs.getInt("estatus"));
            c.setSucursal(s);
            c.setPersona(p);

            clientes.add(c);
        }
        rs.close();
        psmt.close();
        conexion.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return clientes;
}
}
