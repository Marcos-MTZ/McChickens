/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm504.mcchickens.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import org.utl.ldsm504.mcchickens.bd.ConexionMySQL;
import org.utl.ldsm504.mcchickens.bd.DataEmpleado;
import org.utl.ldsm504.mcchickens.model.Empleado;
import org.utl.ldsm504.mcchickens.model.Persona;
import org.utl.ldsm504.mcchickens.model.Sucursal;

public class ControllerEmpleado {

    public Empleado agregarEmpleado(Empleado e) {
        ConexionMySQL conexion = new ConexionMySQL(); //conexion a la bd
        Connection con = null;
        CallableStatement clst = null;
        int idPersona = 0, idEmpleado = 0;
        String usuario = "";
        String query = "{CALL sp_insert_empleado(?,?,?,?,?,?,?,?)}";
        try {
            con = conexion.open();
            clst = con.prepareCall(query);
            clst.setString(1, e.getPersona().getNombre());
            clst.setString(2, e.getPersona().getApellido());
            clst.setString(3, e.getTelefono());
            clst.setInt(4, e.getSucursal().getIdSucursal());
            clst.setString(5, e.getContrasenia());
            clst.registerOutParameter(6, Types.INTEGER);
            clst.registerOutParameter(7, Types.INTEGER);
            clst.registerOutParameter(8, Types.VARCHAR);
            clst.execute();

            idPersona = clst.getInt(6);
            idEmpleado = clst.getInt(7);
            usuario = clst.getString(8);
            
            e.setIdEmpleado(idEmpleado);
            e.getPersona().setIdPersona(idPersona);
            e.setUsuario(usuario);
            e.setEstatus(1);

            clst.close();
            con.close();
            conexion.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return e;
    }

     public void deleteEmpleado(int id){
        ConexionMySQL conexion = new ConexionMySQL();
        Connection con = null;
        CallableStatement clst = null;
        String query = "{CALL sp_delete_empleado(?)}";
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
    
    public void updateEmpleado(Empleado e){
        ConexionMySQL conexion = new ConexionMySQL();
        Connection con = null;
        String query = "CALL sp_update_empleado(?,?,?,?,?,?)";
        CallableStatement clst = null;
        try {
            con = conexion.open();
            clst = con.prepareCall(query);
            clst.setInt(1, e.getIdEmpleado());
            clst.setInt(2, e.getSucursal().getIdSucursal());
            clst.setString(3, e.getTelefono());
            clst.setString(4, e.getPersona().getNombre());
            clst.setString(5, e.getPersona().getApellido());
            clst.setString(6, e.getContrasenia());
            
            clst.execute();
            
            clst.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Empleado> getAll() {
        List<Empleado> empleados = new ArrayList<>();
        String query = "SELECT * FROM v_empleados";
        ConexionMySQL con = new ConexionMySQL();
        try {
            Connection conexion = con.open();
            PreparedStatement psmt = conexion.prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            while (rs.next()) {
                Sucursal s = new Sucursal();
                Empleado e = new Empleado();
                Persona p = new Persona();
                e.setPersona(p);
                s.setPlaza(rs.getString("plaza"));
                s.setDomicilio(rs.getString("domicilio"));
                s.setLatitud(rs.getDouble("latitud"));
                s.setLongitud(rs.getDouble("longitud"));
                s.setIdSucursal(rs.getInt("id_sucursal"));

                e.setSucursal(s);
                e.getPersona().setNombre(rs.getString("nombre"));
                e.getPersona().setApellido(rs.getString("apellido"));
                e.setTelefono(rs.getString("telefono"));
                e.setIdEmpleado(rs.getInt("id_empleado"));
                e.setUsuario(rs.getString("usuario"));        
                e.setEstatus(rs.getInt("estatus"));           
                e.setContrasenia(rs.getString("contrasenia")); 

                empleados.add(e);
            }
            rs.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleados;
    }
}
