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
public class Stock extends Prenda{
    
    int cantidad;

    public Stock() {
        super();
    }

    public Stock(String tipo, int costo, String talle, String marca, String color, int cantidad) {
        super(tipo, costo, talle, marca, color);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Stock{"
                + "\nprenda=" + super.toString()
                + ", \ncantidad=" + cantidad + '}';
    }
    
    
}
