/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm504.mcchickens.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Types;
import org.utl.ldsm504.mcchickens.bd.ConexionMySQL;
import org.utl.ldsm504.mcchickens.bd.DataSucursal;
import org.utl.ldsm504.mcchickens.model.Empleado;
import org.utl.ldsm504.mcchickens.model.Sucursal;

/**
 *
 * @author uriel
 */
public class ControllerSucursal {
    
    public Sucursal agregarSucursal(Sucursal s) {
        ConexionMySQL conexion = new ConexionMySQL(); //conexion a la bd
        Connection con = null;
        CallableStatement clst = null;
        int idSucursal = 0;
        String query = "{CALL sp_insert_sucursal(?,?,?,?,?)}";
        try {
            con = conexion.open();
            clst = con.prepareCall(query);
            clst.setString(1, s.getDomicilio());
            clst.setString(2, s.getPlaza());
            clst.setDouble(3, s.getLatitud());
            clst.setDouble(4, s.getLongitud());
            clst.registerOutParameter(5, Types.INTEGER);

            clst.execute();

            idSucursal = clst.getInt(5);
            
            s.setIdSucursal(idSucursal);
            s.setEstatus(1);

            clst.close();
            con.close();
            conexion.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return s;
    }
    
    public void deleteSucursal(int id){
        ConexionMySQL conexion = new ConexionMySQL();
        Connection con = null;
        CallableStatement clst = null;
        String query = "{CALL sp_delete_sucursal(?)}";
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
    
    public void updateSucursal(Sucursal s){
        ConexionMySQL conexion = new ConexionMySQL();
        Connection con = null;
        String query = "CALL sp_update_sucursal(?,?,?,?,?)";
        CallableStatement clst = null;
        try {
            con = conexion.open();
            clst = con.prepareCall(query);
            clst.setInt(1, s.getIdSucursal());
            clst.setString(2, s.getDomicilio());
            clst.setString(3, s.getPlaza());
            clst.setDouble(4, s.getLatitud());
            clst.setDouble(5, s.getLongitud());
            
            clst.execute();
            
            clst.close();
            conexion.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Sucursal> getAll(){
        
        List<Sucursal> sucursales = new ArrayList<>();
        String query = "SELECT * FROM v_sucursales";
        ConexionMySQL con = new ConexionMySQL();
        try {
            Connection conexion = con.open();
            PreparedStatement psmt = conexion.prepareStatement(query);
            ResultSet rs = psmt.executeQuery();
            while(rs.next()){
                Sucursal s = new Sucursal();
                
                s.setPlaza(rs.getString("plaza"));
                s.setDomicilio(rs.getString("domicilio"));
                s.setLatitud(rs.getDouble("latitud"));
                s.setLongitud(rs.getDouble("longitud"));
                s.setIdSucursal(rs.getInt("idSucursal"));
                
                sucursales.add(s);
            }
            rs.close();
            psmt.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sucursales;
    }
    
    
}
