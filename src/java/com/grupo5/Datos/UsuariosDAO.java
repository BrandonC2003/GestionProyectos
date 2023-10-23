
package com.grupo5.Datos;
package com.grupo5.modelo;

import com.grupo5.config.Conexion;
import com.grupo5.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuariosDAO {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
    public Usuarios validar(String email, String clave){
       Usuarios us = new Usuarios();
       String sql="select * from usuario where Email=? and Clave=?";
    try{
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,email);
            stmt.setString(2, clave);
            rs = stmt.executeQuery();
            
            while(rs.next()){
            
                us.setEmail(rs.getString("Email"));
                us.setEmail(rs.getString("Clave"));
            }
       }
       catch(Exception e)
       {
       }
     
        return us;
    }
}
