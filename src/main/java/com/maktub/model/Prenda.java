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
public class Prenda {
    private String tipo;
    private int costo;
    private String talle;
    private String marca;
    private String color;

    public Prenda() {
    }

    public Prenda(String tipo, String talle, String marca, String color) {
        this.tipo = tipo;
        this.talle = talle;
        this.marca = marca;
        this.color = color;
    }

    public Prenda(String tipo, int costo, String talle, String marca, String color) {
        this.tipo = tipo;
        this.costo = costo;
        this.talle = talle;
        this.marca = marca;
        this.color = color;
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

   
    @Override
    public String toString() {
        return "Prenda{" +
                "\ntipo=" + tipo +
                ", \ncosto=" + costo +
                ", \ntalle=" + talle +
                ", \nmarca=" + marca +
                ", \ncolor=" + color + '}';
    }
    
    
    
}
