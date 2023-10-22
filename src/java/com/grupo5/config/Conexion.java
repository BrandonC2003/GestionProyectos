package com.grupo5.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    // Variables a utilizar
    private static final String USUARIO = "root";
    private static final String PASS = "";
    private static final String URL = "jdbc:mysql://localhost:3306/gestionproyectos";
    // variable para gestionar la conexion
    private static Connection conexion = null;
    
    // metodo para realizar la conexion
    public static Connection conectarse(){
        try{
            // cargar el driver para la conexion
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Obtener la conexion a la BD
            conexion = DriverManager.getConnection(URL, USUARIO, PASS);
            
            // para probar
            System.out.println("Conexion exitosa");
            
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error al conectarse a la BD " + e);
        }
        return conexion;
    }
    
    // Metodos para cerrar conexion
    public static void close(Connection conn){
        try{
            conn.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }

    public static void close(ResultSet rs){
        try{
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt){
        try{
            stmt.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
}
