
package com.grupo5.Datos;

import com.grupo5.config.Conexion;
import com.grupo5.modelo.Proyectos;
import com.grupo5.modelo.Usuarios;
import com.grupo5.modelo.Grupos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;

public class GruposDAO {
    
    public Grupos Teams(int idGrupos, int idProyecto, int idUsuario, String rol){
    Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        Grupos teams = new Grupos();
        
        String sql="select * from usuario where IdGrupo=?";
        
         try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idGrupos);
            stmt.setInt(2, idProyecto);
            stmt.setInt(3, idUsuario);
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                teams.setIdGrupo(rs.getInt("IdGrupos"));   
            }
         }catch(SQLException e)
         {
         }
    
    return teams;
    }
    
}
