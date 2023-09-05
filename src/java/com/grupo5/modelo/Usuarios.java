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
public class Usuarios {
    private int IdUsuario;
    private String Email;
    private String Nombre;
    private String Apellido;
    private String Clave;
    private boolean Confirmacion;
    private String AccesToken;
    private byte[] Foto;
    private List<Proyectos> UsuarioGrupo;
    private List<Tareas> TareaUsuario;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public boolean isConfirmacion() {
        return Confirmacion;
    }

    public void setConfirmacion(boolean Confirmacion) {
        this.Confirmacion = Confirmacion;
    }

    public String getAccesToken() {
        return AccesToken;
    }

    public void setAccesToken(String AccesToken) {
        this.AccesToken = AccesToken;
    }

    public byte[] getFoto() {
        return Foto;
    }

    public void setFoto(byte[] Foto) {
        this.Foto = Foto;
    }

    public List<Proyectos> getUsuarioGrupo() {
        return UsuarioGrupo;
    }

    public void setUsuarioGrupo(List<Proyectos> UsuarioGrupo) {
        this.UsuarioGrupo = UsuarioGrupo;
    }

    public List<Tareas> getTareaUsuario() {
        return TareaUsuario;
    }

    public void setTareaUsuario(List<Tareas> TareaUsuario) {
        this.TareaUsuario = TareaUsuario;
    }
    
    
}
