/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.view;

import java.util.Date;

/**
 *
 * @author Nico
 */
public class VentaView {
    private String nombreCli;
    private int monto;
    private boolean estadoPago;
    private String formaPago;
    Date fecha = new Date();
    private String envio;
    private int numeroVenta;
    private String tipo;
    private String talle;
    private String marca;
    private String color;

    public VentaView() {
    }

    public VentaView(int monto){
        
        this.monto = monto;
    }
    public VentaView(int idVenta, String nombreCli, int monto, boolean estadoPago, String formaPago, String envio, String tipo, String talle, String marca, String color) {
        
        this.nombreCli = nombreCli;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.formaPago = formaPago;
        this.envio = envio;
        this.tipo = tipo;
        this.talle = talle;
        this.marca = marca;
        this.color = color;
    }

     public VentaView(int idVenta, String nombreCli, int monto, boolean estadoPago, String formaPago, String envio, String tipo, String talle, String marca, String color, int numeroVenta) {
        
        this.nombreCli = nombreCli;
        this.monto = monto;
        this.estadoPago = estadoPago;
        this.formaPago = formaPago;
        this.envio = envio;
        this.tipo = tipo;
        this.talle = talle;
        this.marca = marca;
        this.color = color;
        this.numeroVenta = numeroVenta;
    }

    public String getNombreCli() {
        return nombreCli;
    }

    public void setNombreCli(String nombreCli) {
        this.nombreCli = nombreCli;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public boolean isEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(boolean estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public int getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(int numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    @Override
    public String toString() {
        return "VentaView{" + "nombreCli=" + nombreCli + ", monto=" + monto + ", estadoPago=" + estadoPago + ", formaPago=" + formaPago + ", fecha=" + fecha + ", envio=" + envio + ", numeroVenta=" + numeroVenta + ", tipo=" + tipo + ", talle=" + talle + ", marca=" + marca + ", color=" + color + '}';
    }

    
    
    
    
}
