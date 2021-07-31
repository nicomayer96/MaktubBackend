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
public class Venta extends Prenda{
    private String nombreCli;
    private int monto;
    private String formaPago;
    Date fecha = new Date();
    
    private String envio;
    private int numeroVenta;
    

    public Venta() {
        super();
    }
   
    public Venta(String tipo, String talle, String marca, String color, String nombreCli, int monto, String formaPago, Date fecha, String envio) {
        super(tipo, talle, marca, color);
        this.nombreCli = nombreCli;
        this.monto = monto;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.envio = envio;
        
    }
    public Venta(String tipo, String talle, String marca, String color, String nombreCli, int monto, String formaPago, Date fecha, String envio, int numeroVenta) {
        super(tipo, talle, marca, color);
        this.nombreCli = nombreCli;
        this.monto = monto;
        this.formaPago = formaPago;
        this.fecha = fecha;
        this.envio = envio;
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

    public String getEnvio() {
        return envio;
    }

    public void setEnvio(String envio) {
        this.envio = envio;
    }

    public void setMonto(int monto) {
        this.monto = monto;
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

    public int getNumeroVenta() {
        return numeroVenta;
    }

    public void setNumeroVenta(int numeroVenta) {
        this.numeroVenta = numeroVenta;
    }

    @Override
    public String toString() {
        return "Venta{" + "nombreCli=" + nombreCli +
                ", monto=" + monto +
                ", formaPago=" + formaPago +
                ", fecha=" + fecha +
                ", envio=" + envio +
                ", numeroVenta=" + numeroVenta + '}';
    }

    

    
    
    
}
