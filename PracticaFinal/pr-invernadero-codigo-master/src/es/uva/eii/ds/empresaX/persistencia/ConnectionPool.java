package es.uva.eii.ds.empresaX.persistencia;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

public class ConnectionPool {

    private static ConnectionPool conexion;
    private static Connection conn;

    private ConnectionPool() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/Invernadero", "root", "root");
        
        } catch (ClassNotFoundException ce){
            System.err.println(ce);
        } catch (SQLException sqle){
            System.err.println(sqle);
        }
    }

    public static ConnectionPool getInstance() {
        if (conexion == null) {
            return new ConnectionPool();
        }else{
            return conexion;
        }
    }

    public Connection getConnection() {
        return conn;
    }
    
    public PreparedStatement getPreparedStatement(String s){
        PreparedStatement ps = null;
        try{
            ps = getConnection().prepareStatement(s);
        } catch(SQLException sqle){
            System.err.println(sqle);
        }
        return ps;
    }
}