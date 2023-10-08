/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.Datos;

import com.grupo5.modelo.Proyectos;
import java.util.List;


/**
 *
 * @author brand
 */
public class ProyectosDAO {
    
     //Declarar variables para las consultas
    private final String LISTAR= "select p.IdProyecto, e.IdEstado,e.Estado, e.Indice AS IndiceEstado, "
            + "t.IdTarea,t.Tarea, t.Indice as IndiceTarea, t.FechaFin, CONCAT(u.Nombre,' ',u.Apellido) AS NombreUsuario, "
            + "t.Realizada from Proyectos p "
            + "inner JOIN estados e ON e.IdProyecto = p.IdProyecto "
            + "INNER JOIN tareas t on t.IdEstado = e.IdEstado "
            + "INNER JOIN usuario_tarea ut ON ut.IdTarea = t.IdTarea "
            + "inner JOIN usuarios u on u.IdUsuario = ut.IdUsuario "
            + "WHERE p.IdProyecto = ? "
            + "group BY p.IdProyecto, e.IdEstado "
            + "ORDER BY e.Indice, t.Indice ASC";
    private final String BUCAR_POR_ID = "SELECT IdProyecto, Proyecto, Descripcion, Git, UsuarioInserta, FechaInserta  FROM proyectos WHERE IdProyecto=?";
    private final String INSERTAR = "INSERT INTO proyectos(Proyecto, Descripcion, Git, UsuarioInserta, FechaInserta) VALUES (?, ? , ?, ?, NOW())";
    private final String ACTUALIZAR = "UPDATE proyectos SET Proyecto=?, Descripcion=?, Git = ? WHERE IdProyecto = ?";
    private final String ELIMINAR = "DELETE FROM proyectos WHERE IdProyeto = ?";
    
    /**
     * Este metodo consulta a la base de datos todos los elementos pertenecientes a 
     * un proyecto especifico.
     * @param idProyecto
     * @return lista de proyecto
     */
    public List<Proyectos> listarProyecto(int idProyecto){
        return null;
    }
    
    /**
     * Este metodo sirve para buscar por su ID un proyecto en especifico
     * @param idProyecto
     * @return proyecto
     */
    public Proyectos buscarPorID(int idProyecto){
        return null;
    }
    
    /**
     * Metodo para insertar un nuevo proyecto
     * @param proyecto
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo algun inconveniente
     */
    public String insertarProyecto(Proyectos proyecto){
        return null;
    }
    /**
     * Metodo para modificar los datos generales del proyecto
     * @param proyecto
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo algun inconveniente
     */
    public String actulizarProyecto(Proyectos proyecto){
        return null;
    }
    
    /**
     * Metodo para eliminar un proyecto
     * @param idProyecto
     * @return true si se elimino | false si ocurrio un error
     */
    public boolean eliminarProyecto(int idProyecto){
        return false;
    }
}