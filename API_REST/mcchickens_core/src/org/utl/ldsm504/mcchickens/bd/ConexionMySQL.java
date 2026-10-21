/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.utl.ldsm504.mcchickens.bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author uriel
 */
public class ConexionMySQL {
    Connection conn;
    public Connection open(){
    String user = "root";
    String password = "root";
    
    String url = "jdbc:mysql://127.0.0.1:3306/mcchickens?useSSL=false&useUnicode=true&characterEnconding=";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            return conn;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    public void close(){
        try {
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Excepcion controlada");
        }
    }
}
