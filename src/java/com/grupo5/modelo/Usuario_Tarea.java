/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.modelo;

/**
 *
 * @author brand
 */
public class Usuario_Tarea {
    private int IdusuarioTarea;
    private Usuarios usuario;
    private Tareas tarea;

    public int getIdusuarioTarea() {
        return IdusuarioTarea;
    }

    public void setIdusuarioTarea(int IdusuarioTarea) {
        this.IdusuarioTarea = IdusuarioTarea;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Tareas getTarea() {
        return tarea;
    }

    public void setTarea(Tareas tarea) {
        this.tarea = tarea;
    }
}
