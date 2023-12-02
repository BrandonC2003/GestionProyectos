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
        
        List<Proyectos> proyectos = new ProyectosDAO().listarProyectos();
        
        for(Proyectos proy : proyectos){
            System.out.println("id="+proy.getIdProyecto());
            System.out.println("Proyect="+proy.getProyecto());
        }
        
        Tareas tareas = new TareasDAO().buscarPorId(31);
        
        System.out.println(tareas.getTarea());
        
        
    }
}
