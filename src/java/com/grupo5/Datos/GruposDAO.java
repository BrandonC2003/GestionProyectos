
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
    
        private final String LISTAR = "select g.IdGrupos, p.IdProyectos AS p.Proyecto,u.IdUsuario AS Nombre"
            + "CONCAT(u.Nombre,' ',u.Apellido) AS NombreUsuario, "
            + "t.Realizada from Grupos g "
            + "INNER JOIN grupos p ON e.IdGrupos = p.IdGrupos "
            + "LEFT JOIN usuario_grupo ut ON ut.IdUsuario = t.IdUsuario "
            + "LEFT JOIN usuarios u on u.IdUsuario = ut.IdUsuario "
            + "WHERE g.IdGrupo = ? "
            + "group BY g.IdGrupo, p.IdProyecto "
            + "ORDER BY u.Nombre, p.grupo ASC";
    private final String BUCAR_POR_ID = "SELECT IdProyecto, Proyecto, Descripcion, Git, UsuarioInserta, FechaInserta  FROM proyectos WHERE IdProyecto=?";
    private final String INSERTAR = "INSERT INTO grupos(IdGrupo, IdUsuario, IdProyecto,Rol) VALUES (?, ? , ?, ?)";
    private final String ACTUALIZAR = "UPDATE proyectos SET grupos=?, IdUsuario=?, IdProyecto = ? WHERE IdGrupo = ?";
    private final String ELIMINAR = "DELETE FROM grupos WHERE IdGrupo = ?";
    
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
            rs = stmt.executeQuery();
            
            
            while(rs.next()){
                teams.setIdGrupo(rs.getInt("IdGrupos"));
                
            }
         }catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
    
    return teams;
    }
    
}
