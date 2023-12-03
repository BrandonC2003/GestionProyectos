package com.grupo5.Datos;

import com.grupo5.config.Conexion;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Proyectos;
import com.grupo5.modelo.Usuarios;
import com.grupo5.modelo.Grupos;
import com.grupo5.modelo.Tareas;
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

    private final String LISTAR = "select \n"
            + "	g.IdGrupo,\n"
            + "    g.IdProyecto,\n"
            + "    u.IdUsuario,\n"
            + "    g.Rol,\n"
            + "    p.Proyecto,\n"
            + "    u.Nombre,\n"
            + "    u.Apellido,\n"
            + "    u.Email\n"
            + "FROM\n"
            + "	grupos g \n"
            + "    JOIN usuarios u ON g.IdUsuario = u.IdUsuario\n"
            + "    JOIN proyectos p ON g.IdProyecto = p.IdProyecto\n"
            + "WHERE p.IdProyecto = ?";
    private final String INSERTAR = "INSERT INTO grupos(IdUsuario, IdProyecto,Rol) VALUES (? , ?, ?)";
    private final String ACTUALIZAR = "UPDATE grupos SET Rol=? WHERE IdUsuario = ? AND IdProyecto = ?";
    private final String ELIMINAR = "DELETE FROM grupos WHERE IdUsuario = ? AND IdProyecto = ?";
    private final String BUSCAR = "SELECT * FROM grupos WHERE IdUsuario = ? AND IdProyecto = ?";
    private final String ENCONTRAR_USUARIO = "SELECT IdUsuario,Nombre,Apellido FROM usuarios WHERE Email = ?";


    public Proyectos ObtenerGrupos(int idProyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Grupos> gruposList = new ArrayList<>();
        //aqui se almacenaran los id de estados, para que no se repitan los datos.
        Proyectos proyecto = new Proyectos();
        
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(LISTAR);
            stmt.setInt(1, idProyecto);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Grupos grupo = new Grupos();
                Usuarios usuario = new Usuarios();
                usuario.setIdUsuario(rs.getInt("IdUsuario"));
                usuario.setNombre(rs.getString("Nombre"));
                usuario.setApellido(rs.getString("Apellido"));
                usuario.setEmail(rs.getString("Email"));
                grupo.setUsuario(usuario);
                grupo.setRol(rs.getString("Rol"));
                gruposList.add(grupo);
            }

            proyecto.setProyectoGrupo(gruposList);
            proyecto.setIdProyecto(idProyecto);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return proyecto;
    }

    public String AgregarGrupo(int idUsuario, int idProyecto, String rol) {
        Grupos grupo = new Grupos();

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(INSERTAR);
            ps.setInt(1, idUsuario);
            ps.setInt(2, idProyecto);
            ps.setString(3, rol);
            ps.execute();
            
            return null;
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
            return ex.getMessage();
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            //Conexion.close(rs);
        }
    }

    public String ActualizarGrupo(int idUsuario, int idProyecto, String rol) {
        Grupos grupo = new Grupos();

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(ACTUALIZAR);
            ps.setString(1, rol);
            ps.setInt(2, idUsuario);
            ps.setInt(3, idProyecto);
            ps.execute();
            return null;
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            return ex.getMessage();
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
    }

    public boolean EliminarGrupo(int idUsuario, int idProyecto) {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(ELIMINAR);
            ps.setInt(1,idUsuario);
            ps.setInt(2,idProyecto);
            ps.execute();
            return true;
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            return false;
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
        }
    }
    
    private boolean exist(int idUsuario, int idProyecto){
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(BUSCAR);
            ps.setInt(1,idUsuario);
            ps.setInt(2,idProyecto);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            return false;
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            return true;
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
    }
    
    public Usuarios encontrarUsuario(String email){
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuarios usuario = new Usuarios();
        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(ENCONTRAR_USUARIO);
            ps.setString(1,email);
            rs = ps.executeQuery();
            rs.next();
            
            usuario.setIdUsuario(rs.getInt("IdUsuario"));
            usuario.setNombre(rs.getString("Nombre"));
            usuario.setApellido(rs.getString("Apellido"));
            return usuario;
        } catch (Exception ex) {
            System.out.println("Error " + ex);
            return usuario;
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
    }

}
