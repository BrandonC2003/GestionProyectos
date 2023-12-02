package com.grupo5.Datos;

import com.grupo5.config.Conexion;
import com.grupo5.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsuariosDAO {

     private final String INSERTAR="INSERT INTO usuarios(Nombre, Apellido, Email, Clave) VALUES(?, ?, ?, ?)";
    //private final String LISTAR_USUARIO = "SELECT IdUsuario, Nombre FROM usuarios";
    //private final String ACTUALIZAR = "UPDATE usuarios SET Nombre = ?, Apellido = ? Email = ?, Clave = ? WHERE IdUsuario = ?";
    private final String ACTUALIZAR = "UPDATE usuarios SET Nombre = ?, Apellido = ? WHERE IdUsuario = ?";
    private final String OBTENER_USUARIO = "SELECT IdUsuario, Nombre, Apellido, Email, Clave FROM usuarios WHERE IdUsuario = ?";
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    public boolean validar(String email, String clave) {
        Usuarios us = new Usuarios();
        String sql = "select * from usuarios where Email=? and Clave=?";
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2, clave);
            rs = stmt.executeQuery();

            while (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificar(Usuarios use) {
        Usuarios us = new Usuarios();
        //String sql = "UPDATE usuarios SET Nombre = ?, Apellido = ? WHERE IdUsuario = ?";
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(ACTUALIZAR);
            stmt.setString(1, use.getNombre());
            stmt.setString(2, use.getApellido());
            stmt.setInt(3, use.getIdUsuario());
            rs = stmt.executeQuery();

            while (rs.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean InsertarUsuario(Usuarios us) {
         
        //String sql = "INSERT INTO usuarios(Nombre, Apellido, Email, Clave) VALUES(?, ?, ?, ?)";     
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(INSERTAR);
            stmt.setString(1, us.getNombre());
            stmt.setString(2, us.getApellido());
            stmt.setString(3, us.getEmail());
            stmt.setString(4, us.getClave());
            stmt.execute();

            return true;
        } catch (Exception e) {
           // System.out.print(e);
            return false;
        }
    }
    
    public boolean clave(String clave, int IdUsuario){

        String sql = "UPDATE usuarios SET Clave = ? WHERE IdUsuario = ?";
       try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(sql);           
            stmt.setInt(2, IdUsuario);
            stmt.setString(1, clave);
            
            stmt.executeQuery();
            
            return true;
       }
       catch(Exception e)
       {
           return false;
       }   
        
        
    }
    
    
    public Usuarios obtenerUsuario(int idUsuario) {
        Usuarios us = new Usuarios();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(OBTENER_USUARIO);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            
            if (rs.next()) {
                us = new Usuarios();
                // Datos de usuario
                us.setIdUsuario(rs.getInt("IdUsuario"));               
                us.setNombre(rs.getString("Nombre"));
                us.setNombre(rs.getString("Apellido"));
    
            }

            
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
        
        return us;
    }

}
