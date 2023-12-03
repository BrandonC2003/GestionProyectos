/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.modelo;

import java.util.Date;
import java.util.List;

/**
 *
 * @author brand
 */
public class Proyectos {
    private int IdProyecto;
    private String Proyecto;
    private String Descripcion;
    private String Git;
    private String UsuarioInserta;
    private Date FechaInserta;
    private List<Grupos> ProyectoGrupo;
    private List<Estados> estados;

    

    public int getIdProyecto() {
        return IdProyecto;
    }

    public void setIdProyecto(int IdProyecto) {
        this.IdProyecto = IdProyecto;
    }

    public String getProyecto() {
        return Proyecto;
    }

    public void setProyecto(String Proyecto) {
        this.Proyecto = Proyecto;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getGit() {
        return Git;
    }

    public void setGit(String Git) {
        this.Git = Git;
    }

    public String getUsuarioInserta() {
        return UsuarioInserta;
    }

    public void setUsuarioInserta(String UsuarioInserta) {
        this.UsuarioInserta = UsuarioInserta;
    }

    public Date getFechaInserta() {
        return FechaInserta;
    }

    public void setFechaInserta(Date FechaInserta) {
        this.FechaInserta = FechaInserta;
    }

    public List<Grupos> getProyectoGrupo() {
        return ProyectoGrupo;
    }

    public void setProyectoGrupo(List<Grupos> proyectoGrupo) {
        this.ProyectoGrupo = proyectoGrupo;
    }

    public List<Estados> getEstados() {
        return estados;
    }

    public void setEstados(List<Estados> estados) {
        this.estados = estados;
    }

    @Override
    public String toString() {
        return "Proyectos{" + "IdProyecto=" + IdProyecto + ", Proyecto=" + Proyecto + ", Descripcion=" + Descripcion + ", Git=" + Git + ", UsuarioInserta=" + UsuarioInserta + ", FechaInserta=" + FechaInserta + ", ProyectoGrupo=" + ProyectoGrupo + ", estados=" + estados + '}';
    }
    
    
}
