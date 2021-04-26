/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.view;

/**
 *
 * @author Nico
 */
public class StockView {
    private String tipo;
    private int costo;
    private String talle;
    private String marca;
    private String color;
    int cantidad;

    public StockView() {
    }

    public StockView(String tipo, int costo, String talle, String marca, String color, int cantidad) {
        this.tipo = tipo;
        this.costo = costo;
        this.talle = talle;
        this.marca = marca;
        this.color = color;
        this.cantidad = cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public String getTalle() {
        return talle;
    }

    public void setTalle(String talle) {
        this.talle = talle;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "StockView{"
                + "\ntipo=" + tipo
                + ",\ncosto=" + costo
                + ",\ntalle=" + talle
                + ",\nmarca=" + marca
                + ",\ncolor=" + color
                + ",\ncantidad=" + cantidad + '}';
    }
    
}
