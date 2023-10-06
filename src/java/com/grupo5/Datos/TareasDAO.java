/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.Datos;

import com.grupo5.modelo.Tareas;
import java.util.List;

/**
 *
 * @author brand
 */
public class TareasDAO {
    
    //Declarar variables para las consultas
    private final String BUCAR_POR_ID = "SELECT IdTarea,IdEstado, Tarea, Descripcion, FechaInicio, FechaFin, UsuarioInserta, FechaInserta, Realizada FROM tareas WHERE IdTarea = ?";
    private final String LISTAR_POR_USUARIO = "SELECT t.IdTarea,t.IdEstado, t.Tarea, t.Descripcion, p.Proyecto, t.FechaInicio, t.FechaFin, t.UsuarioInserta, t.FechaInserta, t.Realizada "
            + "FROM tareas t "
            + "inner JOIN estados e ON e.IdEstado = t.IdEstado "
            + "INNER JOIN proyectos p ON p.IdProyecto = e.IdProyecto "
            + "INNER JOIN usuario_tarea ut ON ut.IdTarea = t.IdTarea "
            + "WHERE ut.IdUsuario = ?";
    private final String INSERTAR = "INSERT INTO tareas(IdEstado, Tarea, Descripcion, FechaInicio, FechaFin, Indice, Predecesor,UsuarioInserta,FechaInserta)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";
    private final String ACTUALIZAR = "UPDATE tareas SET IdEstado = ?, Tarea = ?, Descripcion = ?, FechaInicio = ?, "
            + "FechaFin = ?, Indice = ?, Predecesor = ?, Realizada = ? "
            + "WHERE IdTarea = ?";
    private final String ELIMINAR = "DELETE FROM tareas WHERE IdTarea = ?";
    
    /**
     * Metodo para buscar una tarea especifica por su ID
     * @param idTarea
     * @return Tarea
     */
    public Tareas buscarPorId(int idTarea){
        return null;
    }
    
    /**
     * Metodo para listar las tareas por el usuario al que se le asignaron
     * @param idUsuario
     * @return Lista de tareas
     */
    public List<Tareas> listarTareasPorUsuario(int idUsuario){
        return null;
    }
    
    /**
     * Metodo para insertar tareas
     * @param tarea
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo algun inconveniente
     */
    public String insertarTarea(Tareas tarea){
        return null;
    }
    
    /**
     * Metodo para actulizar las tareas
     * @param tarea
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo algun inconveniente
     */
    public String actualizarTarea(Tareas tarea){
        return null;
    }
    
    /**
     * Metodo para eliminar la tarea con el id especificado
     * @param IdTarea
     * @return true | false
     */
    public boolean eliminarTarea(int IdTarea){
        return false;
    }
    
    //El indice sirve para asignar un orden al tablero
    /**
     * Este metodo obtiene el ultimo indice de una tarea dentro de un estado
     * @param tarea
     * @return indice
     */
    public int obtenerIndice(Tareas tarea){
        return 0;
    }
    
    /**
     * Metodo para desplazar indices cuando se mueva una tarea a otra posicion del 
     * tablero, se recibira la tarea con el indice al cual se desplazo, y desde ese
     * indice hacia arriba, se moveran en una posicion (+1)
     * @param tarea
     * @return true si todo esta bien | false si ocurre algun error
     */
    public boolean desplazarIndices(Tareas tarea){
        return false;
    }
}
