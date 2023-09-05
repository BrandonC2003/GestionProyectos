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
public class Estados {
    private int IdEstado;
    private String Estado;
    private String color;
    private int Indice;
    private List<Tareas> TareasEstados;

    public int getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(int IdEstado) {
        this.IdEstado = IdEstado;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getIndice() {
        return Indice;
    }

    public void setIndice(int Indice) {
        this.Indice = Indice;
    }

    public List<Tareas> getTareasEstados() {
        return TareasEstados;
    }

    public void setTareasEstados(List<Tareas> TareasEstados) {
        this.TareasEstados = TareasEstados;
    }
    
    
}
