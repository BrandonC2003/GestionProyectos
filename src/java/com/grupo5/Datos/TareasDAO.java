/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.Datos;

import com.grupo5.config.Conexion;
import com.grupo5.config.ErrorPersonalizado;
import com.grupo5.modelo.Tareas;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Proyectos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author brand
 */
public class TareasDAO {

    //Declarar variables para las consultas
    private final String BUSCAR_POR_ID = "SELECT IdTarea,IdEstado, Tarea, Descripcion, FechaInicio, FechaFin, UsuarioInserta, FechaInserta, Realizada FROM tareas WHERE IdTarea = ?";
    private final String LISTAR_POR_USUARIO = "SELECT t.IdTarea,t.IdEstado, t.Tarea, t.Descripcion, p.Proyecto, t.FechaInicio, t.FechaFin, t.UsuarioInserta, t.FechaInserta, t.Realizada "
            + "FROM tareas t "
            + "inner JOIN estados e ON e.IdEstado = t.IdEstado "
            + "INNER JOIN proyectos p ON p.IdProyecto = e.IdProyecto "
            + "INNER JOIN usuario_tarea ut ON ut.IdTarea = t.IdTarea "
            + "WHERE ut.IdUsuario = ?";
    private final String INSERTAR = "INSERT INTO tareas(IdEstado, Tarea, Descripcion, FechaInicio, FechaFin, Indice, Predecesor,UsuarioInserta,FechaInserta)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
    private final String ACTUALIZAR = "UPDATE tareas SET IdEstado = ?, Tarea = ?, Descripcion = ?, FechaInicio = ?, "
            + "FechaFin = ?, Predecesor = ?, Realizada = ? "
            + "WHERE IdTarea = ?";
    private final String ELIMINAR = "DELETE FROM tareas WHERE IdTarea = ?";

    private final String OBTENER_ULTIMO_INDICE = "SELECT Indice FROM tareas WHERE IdEstado = ? ORDER BY Indice DESC LIMIT 1";

    /**
     * Metodo para buscar una tarea especifica por su ID
     *
     * @param idTarea
     * @return Tarea
     */
    public Tareas buscarPorId(int idTarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        Tareas tarea = new Tareas();
        Estados estado = new Estados();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(BUSCAR_POR_ID);
            stmt.setInt(1, idTarea);
            rs = stmt.executeQuery();

            rs.next();

            tarea.setIdTarea(rs.getInt("IdTarea"));
            estado.setIdEstado(rs.getInt("IdEstado"));
            tarea.setEstado(estado);
            tarea.setTarea(rs.getString("Tarea"));
            tarea.setDescripcion(rs.getString("Descripcion"));
            tarea.setFechaInicio(rs.getDate("FechaInicio"));
            tarea.setFechaFin(rs.getDate("FechaFin"));
            tarea.setUsuarioInserta(rs.getString("UsuarioInserta"));
            tarea.setFechaInserta(rs.getDate("FechaInserta"));
            tarea.setRealizada(rs.getBoolean("Realizada"));

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return tarea;
    }

    /**
     * Metodo para listar las tareas por el usuario al que se le asignaron
     *
     * @param idUsuario
     * @return Lista de tareas
     */
    public List<Tareas> listarTareasPorUsuario(int idUsuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Tareas> listTarea = new ArrayList<>();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(LISTAR_POR_USUARIO);
            stmt.setInt(1, idUsuario);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Tareas tarea = new Tareas();
                Estados estado = new Estados();
                Proyectos proyecto = new Proyectos();

                tarea.setIdTarea(rs.getInt("IdTarea"));

                proyecto.setProyecto(rs.getString("Proyecto"));
                estado.setIdEstado(rs.getInt("IdEstado"));
                estado.setProyecto(proyecto);
                tarea.setEstado(estado);
                tarea.setTarea(rs.getString("Tarea"));
                tarea.setDescripcion(rs.getString("Descrpcion"));
                tarea.setFechaInicio(rs.getDate("FechaInciio"));
                tarea.setFechaFin(rs.getDate("FechaFin"));
                tarea.setUsuarioInserta(rs.getString("UsuarioInserta"));
                tarea.setFechaInserta(rs.getDate("FechaInserta"));
                tarea.setRealizada(rs.getBoolean("Realizada"));

                listTarea.add(tarea);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
            Conexion.close(rs);
        }
        return listTarea;
    }

    /**
     * Metodo para insertar tareas
     *
     * @param tarea
     * @return id generado si esta todo bien | 0 si hubo algun inconveniente
     */
    public int insertarTarea(Tareas tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int idGenerado = 0;

        Estados estado = tarea.getEstado();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, estado.getIdEstado());
            stmt.setString(2, tarea.getTarea());
            stmt.setString(3, tarea.getDescripcion());
            stmt.setDate(4, (Date) tarea.getFechaInicio());
            stmt.setDate(5, (Date) tarea.getFechaFin());

            int ultimoIndice = obtenerIndice(estado.getIdEstado());

            if (ultimoIndice != 0) {
                stmt.setInt(6, ultimoIndice);
            } else {
                throw new ErrorPersonalizado("Error al obtener el indice");
            }

            stmt.setInt(7, tarea.getPredecesor());
            stmt.setString(8, tarea.getUsuarioInserta());

            stmt.execute();

            // Recuperar las claves generadas
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
            return idGenerado;
        } catch (SQLException | ErrorPersonalizado e) {
            return 0;
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
        }
    }

    /**
     * Metodo para actulizar las tareas
     *
     * @param tarea
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo
     * algun inconveniente
     */
    public String actualizarTarea(Tareas tarea) {
        Connection conn = null;
        PreparedStatement stmt = null;

        Estados estado = tarea.getEstado();
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(ACTUALIZAR);
            stmt.setInt(1, estado.getIdEstado());
            stmt.setString(2, tarea.getTarea());
            stmt.setString(3, tarea.getDescripcion());
            stmt.setDate(4, (Date) tarea.getFechaInicio());
            stmt.setDate(5, (Date) tarea.getFechaFin());
            stmt.setInt(6, tarea.getPredecesor());
            stmt.setBoolean(7, tarea.isRealizada());
            stmt.setInt(8, tarea.getIdTarea());

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
     * Metodo para eliminar la tarea con el id especificado
     *
     * @param IdTarea
     * @return true | false
     */
    public boolean eliminarTarea(int IdTarea) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = Conexion.conectarse();
            stmt = conn.prepareStatement(ELIMINAR);
            stmt.setInt(1, IdTarea);

            stmt.execute();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            Conexion.close(conn);
            Conexion.close(stmt);
        }
    }

    //El indice sirve para asignar un orden al tablero
    /**
     * Este metodo obtiene el ultimo indice de una tarea dentro de un estado
     *
     * @param idEstado
     * @return indice
     */
    private int obtenerIndice(int idEstado) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(OBTENER_ULTIMO_INDICE);
            ps.setInt(1, idEstado);
            rs = ps.executeQuery();

            if (rs.next()) { //validar que el resultado no sea nulo
                return rs.getInt("Indice") + 1;
            }
            return 1;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return 0; //si retorna 0 es porque ocurrio un error
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
    }

    /**
     * Metodo para desplazar indices cuando se mueva una tarea a otra posicion
     * del tablero, se recibira la tarea con el indice al cual se desplazo, y
     * desde ese indice hacia arriba, se moveran en una posicion (+1)
     *
     * @param tarea
     * @return true si todo esta bien | false si ocurre algun error
     */
    public boolean desplazarIndices(Tareas tarea) {
        return false;
    }
}
