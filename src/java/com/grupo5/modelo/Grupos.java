/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.grupo5.modelo;

import java.util.List;

/**
 *
 * @author brand
 */
public class Grupos {
    private int IdGrupo;
    private Usuarios usuario;
    private Proyectos proyectos;
    private String Rol;

    public int getIdGrupo() {
        return IdGrupo;
    }

    public void setIdGrupo(int IdGrupo) {
        this.IdGrupo = IdGrupo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Proyectos getProyectos() {
        return proyectos;
    }

    public void setProyectos(Proyectos proyecto) {
        this.proyectos = proyecto;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String Rol) {
        this.Rol = Rol;
    }
    
    
}
