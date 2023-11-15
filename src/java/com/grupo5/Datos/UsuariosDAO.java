package com.grupo5.Datos;

import com.grupo5.config.Conexion;
import com.grupo5.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuariosDAO {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
    public boolean validar(String email, String clave){
       Usuarios us = new Usuarios();
       String sql="select * from usuarios where Email=? and Clave=?";
    try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2, clave);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                return true;
            }
            return false;
       }
       catch(Exception e)
       {
           return false;
       }
    }
    
    
    public boolean modificar(String nombres, String apellidos, String email, String clave, String confirmacion){
       Usuarios us = new Usuarios();
       String sql = "UPDATE usuarios SET Nombres = ?, Apellidos = ? Email = ?, Clave = ? Confirmacion = ? WHERE IdUsuario = ?";
    try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,nombres);
            stmt.setString(2,apellidos);
            stmt.setString(3,email);
            stmt.setString(4, clave);
            stmt.setString(5,confirmacion);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                return true;
            }
            return false;
       }
       catch(Exception e)
       {
           return false;
       }
    }
    
    
}
