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
    private final String LISTAR_PROYECTOS = "SELECT p.IdProyecto, p.Proyecto FROM proyectos p INNER JOIN grupos g ON g.IdProyecto = p.IdProyecto WHERE g.IdUsuario = ?";
    private final String LISTAR = "select p.IdProyecto, p.Proyecto, p.Descripcion, p.Git, e.IdEstado,e.Estado, e.Color, e.Indice AS IndiceEstado, "
            + "t.IdTarea,t.Tarea, t.Indice as IndiceTarea, t.FechaFin, CONCAT(u.Nombre,' ',u.Apellido) AS NombreUsuario, "
            + "t.Realizada from Proyectos p "
            + "INNER JOIN estados e ON e.IdProyecto = p.IdProyecto "
            + "LEFT JOIN tareas t on t.IdEstado = e.IdEstado "
            + "LEFT JOIN usuario_tarea ut ON ut.IdTarea = t.IdTarea "
            + "LEFT JOIN usuarios u on u.IdUsuario = ut.IdUsuario "
            + "WHERE p.IdProyecto = ? "
            + "group BY p.IdProyecto, e.IdEstado, t.IdTarea "
            + "ORDER BY e.Indice, t.Indice ASC";
    private final String BUCAR_POR_ID = "SELECT IdProyecto, Proyecto, Descripcion, Git, UsuarioInserta, FechaInserta  FROM proyectos WHERE IdProyecto=?";
    private final String INSERTAR = "INSERT INTO proyectos(Proyecto, Descripcion, Git, UsuarioInserta, FechaInserta) VALUES (?, ? , ?, ?, NOW())";
    private final String ACTUALIZAR = "UPDATE proyectos SET Proyecto=?, Descripcion=?, Git = ? WHERE IdProyecto = ?";
    private final String ELIMINAR = "DELETE FROM proyectos WHERE IdProyecto = ?";
    
    //consulta para obtener el rol del usuario  dentro del proyecto.
    private final String OBTENER_ROL = "SELECT ROL FROM grupos WHERE IdUsuario = ? AND IdProyecto=?";

    public List<Proyectos> listarProyectos(int idUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Proyectos proyecto = null;
        List<Proyectos> listProyectos = new ArrayList<>();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(LISTAR_PROYECTOS);
            stmt.setInt(1, idUsuario);

            rs = stmt.executeQuery();

            while (rs.next()) {
                proyecto = new Proyectos();

                proyecto.setIdProyecto(rs.getInt("IdProyecto"));
                proyecto.setProyecto(rs.getString("Proyecto"));

                listProyectos.add(proyecto);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return listProyectos;
    }

    public String obtenerRol(int idUsuario, int idProyecto){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String rol = null;
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(OBTENER_ROL);
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idProyecto);

            rs = stmt.executeQuery();

            rs.next();
            
            rol = rs.getString("Rol");

        } catch (SQLException e) {
            e.printStackTrace(System.out);
            rol = null;
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return  rol;
    }
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

        //aqui se almacenaran los id de estados, para que no se repitan los datos.
        List<Integer> idsEstados = new ArrayList<>();
        int idEstados;
        
        List<Tareas> listTarea= null;
        Estados estado = null;
        boolean existsEstado = false;

        Proyectos proyecto = new Proyectos();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(LISTAR);
            stmt.setInt(1, idProyecto);
            rs = stmt.executeQuery();

            List<Estados> listEstado = new ArrayList<>();

            while (rs.next()) {
                existsEstado=false;
                proyecto.setIdProyecto(rs.getInt("IdProyecto"));
                proyecto.setProyecto(rs.getString("Proyecto"));
                proyecto.setDescripcion(rs.getString("Descripcion"));
                proyecto.setGit(rs.getString("Git"));

                idEstados = rs.getInt("IdEstado");

                //Valido si el estado recuperado aun no esta registrado en mi lista
                if (idsEstados.indexOf(idEstados) == -1) {
                    idsEstados.add(idEstados);
                    estado = new Estados();
                    estado.setIdEstado(idEstados);
                    estado.setEstado(rs.getString("Estado"));
                    estado.setIndice(rs.getInt("IndiceEstado"));
                    estado.setColor(rs.getString("Color"));
                    estado.setProyecto(proyecto);
                    
                    listTarea = new ArrayList<>();
                    existsEstado=true;
                }

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
                
                 if (existsEstado) {
                     listEstado.add(estado);
                 }
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
     * @return ultimo id generado si esta todo bien | 0 si hubo algun
     * inconveniente
     */
    public int insertarProyecto(Proyectos proyecto) {
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

        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, proyecto.getProyecto());
            ps.setString(2, proyecto.getDescripcion());
            ps.setString(3, proyecto.getGit());
            ps.setString(4, proyecto.getUsuarioInserta());
            ps.execute();
            rs = ps.getGeneratedKeys();

            if (rs.next()) {
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
                tarea.setUsuarioInserta(proyecto.getUsuarioInserta());

                tareaDao.insertarTarea(tarea, 0);
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
                tarea.setUsuarioInserta(proyecto.getUsuarioInserta());

                tareaDao.insertarTarea(tarea, 0);
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
                tarea.setUsuarioInserta(proyecto.getUsuarioInserta());

                tareaDao.insertarTarea(tarea, 0);
            }

            return rs.getInt(1);
        } catch (SQLException e) {
            return 0;
        } finally {
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
    public String actualizarProyecto(Proyectos proyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(ACTUALIZAR);
            stmt.setString(1, proyecto.getProyecto());
            stmt.setString(2, proyecto.getDescripcion());
            stmt.setString(3, proyecto.getGit());
            stmt.setInt(4, proyecto.getIdProyecto());

            stmt.execute();
            return null;
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
        }
    }

    /**
     * Metodo para eliminar un proyecto
     *
     * @param idProyecto
     * @return true si se elimino | false si ocurrio un error
     */
    public boolean eliminarProyecto(int idProyecto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(ELIMINAR);
            stmt.setInt(1, idProyecto);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
        }
    }
}
