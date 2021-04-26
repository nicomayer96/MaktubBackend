/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.model;

/**
 *
 * @author Nico
 */
public class DetalleVenta {
    Venta venta;
    Prenda prenda;
    private int cantidad;
    private String comentario;

    public DetalleVenta() {
    }

    public DetalleVenta(Venta venta, Prenda prenda, int cantidad, String comentario) {
        this.venta = venta;
        this.prenda = prenda;
        this.cantidad = cantidad;
        this.comentario = comentario;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Prenda getPrenda() {
        return prenda;
    }

    public void setPrenda(Prenda prenda) {
        this.prenda = prenda;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    @Override
    public String toString() {
        return "DetalleVenta{" +
                "\nventa=" + venta +
                ", \nprenda=" + prenda +
                ", \ncantidad=" + cantidad +
                ", \ncomentario=" + comentario + '}';
    }
    
    
}
