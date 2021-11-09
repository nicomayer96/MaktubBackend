/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.model;

import java.util.Date;

/**
 *
 * @author Nico
 */
public class Gasto {
    
    String descripcion;
    int monto;
    Date fecha = new Date();

    public Gasto() {
    }

    public Gasto(String descripcion, int monto, Date fecha) {
        this.descripcion = descripcion;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Gasto{" + "descripcion=" + descripcion + ", monto=" + monto + ", fecha=" + fecha + '}';
    }
    
    
}
