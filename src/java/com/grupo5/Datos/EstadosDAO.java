/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.Datos;

import com.grupo5.modelo.Estados;

/**
 *
 * @author brand
 */
public class EstadosDAO {
    
     //Declarar variables para las consultas
    private final String BUCAR_POR_ID = "SELECT IdEstado, Estado, Color FROM estados WHERE IdEstado = ?";
    private final String INSERTAR = "INSERT INTO estados(IdProyecto, Estado, Color, Indice) VALUES (?, ?, ?, ?)";
    private final String ACTUALIZAR = "UPDATE estados SET Estado = ?, Color = ?, Indice = ? WHERE IdEstado = ?";
    private final String ELIMINAR = "DELETE FROM estados WHERE IdEstado = ?";
    
    /**
     * Metodo para buscar un estado especifico por su ID
     * @param idEstado
     * @return Estado encontrado
     */
    public Estados buscarPorId(int idEstado){
        return null;
    }
    
    /**
     * Metodo para insertar Estados
     * @param estado
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo algun inconveniente
     */
    public String insertarEstado(Estados estado){
        return null;
    }
    
    /**
     * Metodo para actulizar un estado
     * @param estado
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo algun inconveniente
     */
    public String actualizarEstado(Estados estado){
        return null;
    }
    
    /**
     * Metodo para eliminar un estado
     * @param IdEstado
     * @return true | false
     */
    public boolean eliminarEstado(int IdEstado){
        return false;
    }
    
    //El indice sirve para asignar un orden al tablero
    /**
     * Este metodo obtiene el ultimo indice que tienen asignados los estados dentro de un proyecto
     * retorna el ultimo indice + 1, para que le asignemos al siguiente estado que se inserte
     * @param estado
     * @return indice
     */
    public int obtenerIndice(Estados estado){
        return 0;
    }
    
    /**
     * Metodo para desplazar indices cuando se mueva un estado a otra posicion del 
     * tablero, se recibira el estado con el indice al cual se desplazo, y desde ese
     * indice hacia arriba, se moveran en una posicion (+1)
     * @param estado
     * @return true si todo esta bien | false si ocurre algun error
     */
    public boolean desplazarIndices(Estados estado){
        return false;
    }
}
