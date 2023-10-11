/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.Datos;
import com.grupo5.modelo.Proyectos;
import com.grupo5.modelo.Estados;
import com.grupo5.modelo.Tareas;
import com.grupo5.modelo.Usuarios;
import java.util.List;
/**
 *
 * @author brand
 */
public class MainPruebas {
    public static void main(String[] args) {
        Proyectos proyecto = new ProyectosDAO().obtenerProyecto(1);
        List<Estados> listEstado = proyecto.getEstados();
        
        for(Estados est : listEstado){
            System.out.println("Estado: "+ est.getEstado());
             List<Tareas> listTarea = est.getTareas();
             for(Tareas tar: listTarea){
                 System.out.println("Tarea: "+tar.getTarea());
                 List<Usuarios> listUsuario = tar.getUsuarios();
                 for(Usuarios us : listUsuario){
                     System.out.println("UserName: "+us.getNombre());
                 }
             }
        }
    }
}
