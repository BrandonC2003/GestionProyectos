/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.Datos;

import com.grupo5.config.Conexion;
import com.grupo5.modelo.Proyectos;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Tareas;
import com.grupo5.modelo.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.sql.Date;
/**
 *
 * @author brand
 */
public class ProyectosDAO {

    //Declarar variables para las consultas
    private final String LISTAR = "select p.IdProyecto, e.IdEstado,e.Estado, e.Indice AS IndiceEstado, "
            + "t.IdTarea,t.Tarea, t.Indice as IndiceTarea, t.FechaFin, CONCAT(u.Nombre,' ',u.Apellido) AS NombreUsuario, "
            + "t.Realizada from Proyectos p "
            + "INNER JOIN estados e ON e.IdProyecto = p.IdProyecto "
            + "LEFT JOIN tareas t on t.IdEstado = e.IdEstado "
            + "LEFT JOIN usuario_tarea ut ON ut.IdTarea = t.IdTarea "
            + "LEFT JOIN usuarios u on u.IdUsuario = ut.IdUsuario "
            + "WHERE p.IdProyecto = ? "
            + "group BY p.IdProyecto, e.IdEstado "
            + "ORDER BY e.Indice, t.Indice ASC";
    private final String BUCAR_POR_ID = "SELECT IdProyecto, Proyecto, Descripcion, Git, UsuarioInserta, FechaInserta  FROM proyectos WHERE IdProyecto=?";
    private final String INSERTAR = "INSERT INTO proyectos(Proyecto, Descripcion, Git, UsuarioInserta, FechaInserta) VALUES (?, ? , ?, ?, NOW())";
    private final String ACTUALIZAR = "UPDATE proyectos SET Proyecto=?, Descripcion=?, Git = ? WHERE IdProyecto = ?";
    private final String ELIMINAR = "DELETE FROM proyectos WHERE IdProyeto = ?";

    /**
     * Este metodo consulta a la base de datos todos los elementos
     * pertenecientes a un proyecto especifico.
     *
     * @param idProyecto
     * @return lista de proyecto
     */
    public Proyectos obtenerProyecto(int idProyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Proyectos proyecto = new Proyectos();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(LISTAR);
            stmt.setInt(1, idProyecto);
            rs = stmt.executeQuery();

            List<Estados> listEstado = new ArrayList<>();

            while (rs.next()) {
                Estados estado = new Estados();
                estado.setIdEstado(rs.getInt("IdEstado"));
                estado.setEstado(rs.getString("Estado"));
                estado.setIndice(rs.getInt("IndiceEstado"));
                estado.setProyecto(proyecto);

                List<Tareas> listTarea = new ArrayList<>();

                Tareas tarea = new Tareas();
                tarea.setIdTarea(rs.getInt("IdTarea"));
                tarea.setTarea(rs.getString("Tarea"));
                tarea.setFechaFin(rs.getDate("FechaFin"));
                tarea.setEstado(estado);
                tarea.setRealizada(rs.getBoolean("Realizada"));

                List<Usuarios> listUsuario = new ArrayList<>();
                Usuarios usuario = new Usuarios();
                usuario.setNombre(rs.getString("NombreUsuario"));
                listUsuario.add(usuario);

                tarea.setUsuarios(listUsuario);
                listTarea.add(tarea);

                estado.setTareas(listTarea);
                listEstado.add(estado);
            }

            proyecto.setIdProyecto(idProyecto);
            proyecto.setEstados(listEstado);

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return proyecto;
    }

    /**
     * Este metodo sirve para buscar por su ID un proyecto en especifico
     *
     * @param idProyecto
     * @return proyecto
     */
    public Proyectos buscarPorID(int idProyecto) {
        return null;
    }

    /**
     * Metodo para insertar un nuevo proyecto, el cual tambien creara una 
     * plantilla de proyecto con estados y tareas de ejemplos.
     *
     * @param proyecto
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo
     * algun inconveniente
     */
    public String insertarProyecto(Proyectos proyecto) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        Estados estado = null;
        EstadosDAO estadoDao = new EstadosDAO();
        
        Tareas tarea = null;
        TareasDAO tareaDao = new TareasDAO();
        
        LocalDate fechaI = LocalDate.now();
        Date fechaInicio = Date.valueOf(fechaI);
        
        LocalDate fechaF = fechaI.plusDays(5);
        Date fechaFin = Date.valueOf(fechaF);
        
        try{
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1,proyecto.getProyecto());
            ps.setString(2, proyecto.getDescripcion());
            ps.setString(3, proyecto.getGit());
            ps.setString(4, proyecto.getUsuarioInserta());
            ps.execute();
            rs = ps.getGeneratedKeys();
            
            if(rs.next()){
                proyecto.setIdProyecto(rs.getInt(1));
                
                //primer estado
                estado = new Estados();
                estado.setProyecto(proyecto);
                estado.setEstado("Por hacer");
                estado.setColor("bg-danger");
                
                estado.setIdEstado(estadoDao.insertarEstado(estado));
                
                tarea = new Tareas();
                tarea.setEstado(estado);
                tarea.setTarea("Tarea por hacer 1");
                tarea.setDescripcion("");
                tarea.setFechaInicio(fechaInicio);
                tarea.setFechaFin(fechaFin);
                
                tareaDao.insertarTarea(tarea);
                //Segundo estado
                estado = new Estados();
                estado.setProyecto(proyecto);
                estado.setEstado("En proceso");
                estado.setColor("bg-warning");
                
                estado.setIdEstado(estadoDao.insertarEstado(estado));
                
                tarea = new Tareas();
                tarea.setEstado(estado);
                tarea.setTarea("Tarea en proceso 1");
                tarea.setDescripcion("");
                tarea.setFechaInicio(fechaInicio);
                tarea.setFechaFin(fechaFin);
                
                tareaDao.insertarTarea(tarea);
                //Tercer estado
                estado = new Estados();
                estado.setProyecto(proyecto);
                estado.setEstado("Finalizadas");
                estado.setColor("bg-success");
                
                estado.setIdEstado(estadoDao.insertarEstado(estado));
                
                tarea = new Tareas();
                tarea.setEstado(estado);
                tarea.setTarea("Tarea finalizada 1");
                tarea.setDescripcion("");
                tarea.setFechaInicio(fechaInicio);
                tarea.setFechaFin(fechaFin);
                
                tareaDao.insertarTarea(tarea);
            }
            
            return null;
        }catch(SQLException e){
            return e.getMessage();
        }finally{
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
    }

    /**
     * Metodo para modificar los datos generales del proyecto
     *
     * @param proyecto
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo
     * algun inconveniente
     */
    public String actulizarProyecto(Proyectos proyecto) {
        return null;
    }

    /**
     * Metodo para eliminar un proyecto
     *
     * @param idProyecto
     * @return true si se elimino | false si ocurrio un error
     */
    public boolean eliminarProyecto(int idProyecto) {
        return false;
    }
}
