/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.modelo;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author brand
 */
public class Tareas {
    private int IdTarea;
    private String Tarea;
    private String Descripcion;
    private Date FechaInicio;
    private Date FechaFin;
    private int Indice;
    private int Predecesor;
    private boolean Realizada;
    private String UsuarioInserta;
    private Date FechaInserta;
    private Estados estado;
    private List<Usuarios> Usuarios;

    public int getIdTarea() {
        return IdTarea;
    }

    public void setIdTarea(int IdTarea) {
        this.IdTarea = IdTarea;
    }

    public String getTarea() {
        return Tarea;
    }

    public void setTarea(String Tarea) {
        this.Tarea = Tarea;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public Date getFechaInicio() {
        return FechaInicio;
    }

    public void setFechaInicio(Date FechaInicio) {
        this.FechaInicio = FechaInicio;
    }

    public Date getFechaFin() {
        return FechaFin;
    }

    public void setFechaFin(Date FechaFin) {
        this.FechaFin = FechaFin;
    }

    public int getIndice() {
        return Indice;
    }

    public void setIndice(int Indice) {
        this.Indice = Indice;
    }

    public int getPredecesor() {
        return Predecesor;
    }

    public void setPredecesor(int Predecesor) {
        this.Predecesor = Predecesor;
    }

    public boolean isRealizada() {
        return Realizada;
    }

    public void setRealizada(boolean Realizada) {
        this.Realizada = Realizada;
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

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }
    
    public List<Usuarios> getUsuarios() {
        return Usuarios;
    }

    public void setUsuarios(List<Usuarios> Usuarios) {
        this.Usuarios = Usuarios;
    }

    @Override
    public String toString() {
        return "Tareas{" + "IdTarea=" + IdTarea + ", Tarea=" + Tarea + ", Descripcion=" + Descripcion + ", FechaInicio=" + FechaInicio + ", FechaFin=" + FechaFin + ", Indice=" + Indice + ", Predecesor=" + Predecesor + ", Realizada=" + Realizada + ", UsuarioInserta=" + UsuarioInserta + ", FechaInserta=" + FechaInserta + ", estado=" + estado + ", Usuarios=" + Usuarios + '}';
    }
    
    
}
