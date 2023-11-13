package com.grupo5.Datos;

import com.grupo5.config.Conexion;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Proyectos;
import java.sql.Connection;
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
public class EstadosDAO {

    //Declarar variables para las consultas
    private final String BUSCAR_POR_ID = "SELECT IdEstado, IdProyecto, Estado, Color, Indice FROM estados WHERE IdEstado = ?";
    private final String BUSCAR_POR_PROYECTO = "SELECT IdEstado, IdProyecto, Estado, Color, Indice FROM estados WHERE IdProyecto = ?";
    private final String INSERTAR = "INSERT INTO estados(IdProyecto, Estado, Color, Indice) VALUES (?, ?, ?, ?)";
    private final String ACTUALIZAR = "UPDATE estados SET Estado = ?, Color = ? WHERE IdEstado = ?";
    private final String ELIMINAR = "DELETE FROM estados WHERE IdEstado = ?";
    private final String OBTENER_ULTIMO_INDICE = "SELECT Indice FROM estados WHERE IdProyecto = ? ORDER BY Indice DESC LIMIT 1";
    private final String ACTUALIZAR_INDICE = "UPDATE estados set Indice = ? where IdEstado = ?";
    //Cuando el indice actual es menor que el incide anterior, primero ira el indice actual y luego el anterior.
    private final String MOVER_INDICE_MENOR = "UPDATE estados set Indice = Indice+1 WHERE IdProyecto = ? AND Indice >= ? AND Indice<= ? AND IdEstado != ?";
    //Cuando el indice actual es mayor que el indice anterior, primero ira el indice anterior y luego el indice actual.
    private final String MOVER_INDICE_MAYOR = "UPDATE estados set Indice = Indice-1 WHERE IdProyecto = ? AND Indice >= ? AND Indice<= ? AND IdEstado != ?";
    //Se utilizara cuando se elimine un estado, para que los indices sigan en orden.
    private final String AJUSTAR_INDICES = "UPDATE estados SET Indice = Indice - 1 WHERE IdProyecto = ? AND Indice > ?";

    /**
     * Metodo para buscar un estado especifico por su ID
     *
     * @param idEstado
     * @return Estado encontrado
     */
    public Estados buscarPorId(int idEstado) {
        Estados estado = new Estados();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Proyectos proyecto = new Proyectos();

        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(BUSCAR_POR_ID);
            ps.setInt(1, idEstado);
            rs = ps.executeQuery();
            rs.next();
            estado.setIdEstado(rs.getInt("IdEstado"));
            estado.setEstado(rs.getString("Estado"));
            estado.setColor(rs.getString("Color"));
            estado.setIndice(rs.getInt("Indice"));
            proyecto.setIdProyecto(rs.getInt("IdProyecto"));
            estado.setProyecto(proyecto);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
        return estado;
    }

    public List<Estados> buscarPorProyecto(int idProyecto) {
        Estados estado;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Proyectos proyecto;
        
        List<Estados> estados; 
        estados = new ArrayList<>();

        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(BUSCAR_POR_PROYECTO);
            ps.setInt(1, idProyecto);
            rs = ps.executeQuery();
            while (rs.next()) {
                estado = new Estados();
                proyecto = new Proyectos();
                
                estado.setIdEstado(rs.getInt("IdEstado"));
                estado.setEstado(rs.getString("Estado"));
                estado.setColor(rs.getString("Color"));
                estado.setIndice(rs.getInt("Indice"));
                proyecto.setIdProyecto(rs.getInt("IdProyecto"));
                estado.setProyecto(proyecto);
                
                estados.add(estado);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
        return estados;
    }

    /**
     * Metodo para insertar Estados
     *
     * @param estado
     * @return el id generado | si hay algun error retornara 0
     */
    public int insertarEstado(Estados estado) {

        Proyectos proyecto = estado.getProyecto();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(INSERTAR, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, proyecto.getIdProyecto());
            ps.setString(2, estado.getEstado());
            ps.setString(3, estado.getColor());
            ps.setInt(4, obtenerIndice(proyecto.getIdProyecto()));
            ps.execute();
            //obtengo el id generado
            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            return 0;
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
    }

    /**
     * Metodo para actulizar un estado
     *
     * @param estado
     * @return mensaje de nulo si esta todo bien | mensaje de error si hubo
     * algun inconveniente
     */
    public String actualizarEstado(Estados estado) {
        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(ACTUALIZAR);
            ps.setString(1, estado.getEstado());
            ps.setString(2, estado.getColor());
            ps.setInt(3, estado.getIdEstado());
            ps.execute();
            return null;
        } catch (SQLException e) {
            return e.getMessage();
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
        }
    }

    /**
     * Metodo para eliminar un estado
     *
     * @param idEstado
     * @return true | false
     */
    public boolean eliminarEstado(int idEstado) {
        Connection conexion = null;
        PreparedStatement ps = null;

        Estados estado = buscarPorId(idEstado);
        Proyectos proyecto = estado.getProyecto();
        try {

            //elimino el estado
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(ELIMINAR);
            ps.setInt(1, idEstado);
            ps.execute();

            //ajusto los indices.
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(AJUSTAR_INDICES);
            ps.setInt(1, proyecto.getIdProyecto());
            ps.setInt(2, estado.getIndice());
            ps.execute();
            return true;
        } catch (SQLException e) {
            return false;
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
        }
    }

    //El indice sirve para asignar un orden al tablero
    /**
     * Este metodo obtiene el ultimo indice que tienen asignados los estados
     * dentro de un proyecto retorna el ultimo indice + 1, para que le asignemos
     * al siguiente estado que se inserte
     *
     * @param idProyecto
     * @return indice | 0 para indicar que hay un error.
     */
    private int obtenerIndice(int idProyecto) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(OBTENER_ULTIMO_INDICE);
            ps.setInt(1, idProyecto);
            rs = ps.executeQuery();

            if (rs.next()) { //validar que el resultado no sea nulo
                return rs.getInt("Indice") + 1;
            }
            return 1; //si el resultado es nulo, entonces me dara el indice 1.
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
     * Metodo para desplazar indices cuando se mueva un estado a otra posicion
     * del tablero, se recibira el estado con el indice al cual se desplazo, y
     * desde ese indice hacia arriba o hacia abajo, se moveran en una posicion
     *
     * @param estado
     * @param indiceAnterior
     * @return true si todo esta bien | false si ocurre algun error
     */
    public boolean desplazarIndices(Estados estado, int indiceAnterior) {
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Proyectos proyecto = estado.getProyecto();
        try {
            conexion = Conexion.conectarse();
            ps = conexion.prepareStatement(ACTUALIZAR_INDICE);
            ps.setInt(1, estado.getIndice());
            ps.setInt(2, estado.getIdEstado());
            ps.execute();
            //validar si el indice actual es menor que el indice anterior o biceversa.
            if (estado.getIndice() < indiceAnterior) {
                ps = conexion.prepareStatement(MOVER_INDICE_MENOR);
                ps.setInt(1, proyecto.getIdProyecto());
                ps.setInt(2, estado.getIndice());
                ps.setInt(3, indiceAnterior);
                ps.setInt(4, estado.getIdEstado());
                ps.execute();
            } else {
                ps = conexion.prepareStatement(MOVER_INDICE_MAYOR);
                ps.setInt(1, proyecto.getIdProyecto());
                ps.setInt(2, indiceAnterior);
                ps.setInt(3, estado.getIndice());
                ps.setInt(4, estado.getIdEstado());
                ps.execute();
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        } finally {
            Conexion.close(conexion);
            Conexion.close(ps);
            Conexion.close(rs);
        }
    }
}
