/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.dao;


import com.maktub.model.Venta;
import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nico
 */
public class VentaDao {
                        //Agregar venta donde el idPrenda lo busca con los parametros de la prenda
    public static void agregarVenta(Venta venta) throws Exception{
    Connection cn = ConnectionManager.obtenerConexion();
    String dateString = String.format("%1$tY-%1$tm-%1$td", venta.getFecha());
    
//    SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//    String reformattedStr = (myFormat.format((venta.getFecha())));
    
    
    String sqlAgregar = "insert into ventas (cliente, monto, formaPago, pago, fecha, envio, idPrenda, numeroVenta) " +
            "Values ( '" + venta.getNombreCli() + "', "
            + venta.getMonto() + ", '"
            + venta.getFormaPago() + "', "
            + venta.isEstadoPago() + ", '"
            + dateString + "', '"
            + venta.getEnvio() + "', "
            + "(select idPrenda from prenda where "
            + "tipo = '" + venta.getTipo()
            + "' and talle = '" + venta.getTalle()
            + "' and marca = '" + venta.getMarca()
            + "' and color = '" + venta.getColor() + "' limit 1),"
            + "0" + ")";
        System.out.println(dateString);
        System.out.println(venta.getFecha());
                        //El registro de venta que tienen monto !=0(solo una por venta)
                        //se le agrega el numero de venta siguiente al que estaba
    
    String sqlModificarNumVenta = "UPDATE ventas set numeroVenta = ((SELECT numeroVenta FROM " 
            +"(SELECT MAX(numeroVenta) AS numeroVenta FROM ventas) "
            + "AS numeroVenta))+1 "
            + "WHERE monto > 0 and Cliente like '"
            + venta.getNombreCli() +
            "' and fecha like '" + dateString + "'";
    
                        //El registro de venta que no tiene monto(casos donde se compran mas de una prenda)
                        //mantiene el numero de venta
    
    String sqlModificarNumVenta2 = "UPDATE ventas set numeroVenta = ((SELECT numeroVenta FROM " 
            +"(SELECT MAX(numeroVenta) AS numeroVenta FROM ventas) "
            + "AS numeroVenta)) "
            + "WHERE monto in (0) and Cliente like '"
            + venta.getNombreCli()
            + "' and fecha like '"
            + dateString + "'";
    
                        //Modificar el stock de las prendas vendidas
    
    String sqlModificarStock = "update stock set cantidad = (cantidad-1)"
            + " where idPrenda = (select IDprenda from prenda"
            + " where tipo = '" + venta.getTipo()
            + "' and talle = '" + venta.getTalle()
            + "' and marca = '" + venta.getMarca()
            + "' and color = '" + venta.getColor() + "' limit 1)";
    
    Statement st = cn.createStatement();
    st.execute(sqlAgregar);
    if(venta.getMonto()!= 0){
        st.execute(sqlModificarNumVenta);
    }    
    st.execute(sqlModificarNumVenta2);
    st.execute(sqlModificarStock);
    st.close();
    cn.close();
    }
    
                            //Visualizar las ventas por mes
    
    public static List<Venta> verVentas(int mes) throws Exception{
        List <Venta> ventas = new ArrayList();

        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsultaVentas = "select v.Cliente, v.monto, v.formaPago, v.pago, v.fecha, v.envio, v.numeroVenta,"
                    + " p.tipo, p.talle, p.marca, p.color " +
                "from ventas as v " +
                "inner join prenda as p " +
                "on v.idprenda = p.idPrenda " +
                "where month(fecha) like " + mes +
                    " order by day(fecha)";
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultaVentas);
            
            while(rs.next()){
                Venta venta = new Venta();
                String cliente = rs.getString("Cliente");
                int monto = rs.getInt("monto");
                String fPago = rs.getString("formaPago");
                boolean pago = rs.getBoolean("pago");
                Date fecha = new Date();
                fecha = rs.getDate("fecha");
                String envio = rs.getString("envio");
                int numeroVenta = rs.getInt("numeroVenta");
                String tipo = rs.getString("tipo");
                String talle = rs.getString("talle");
                String marca = rs.getString("marca");
                String color = rs.getString("color");
                
                venta.setNombreCli(cliente);
                venta.setMonto(monto);
                venta.setFormaPago(fPago);
                venta.setEstadoPago(pago);
                venta.setFecha(fecha);
                venta.setEnvio(envio);
                venta.setNumeroVenta(numeroVenta);
                venta.setTipo(tipo);
                venta.setTalle(talle);
                venta.setMarca(marca);
                venta.setColor(color);
                
                ventas.add(venta);
            }
                        st.close();
            cn.close();
    
    return ventas;
}
    
    public static void cambioEstadoPago(Venta venta) throws Exception{
        Connection cn = ConnectionManager.obtenerConexion();
        String sqlCambioEstadoPago = "UPDATE Ventas SET Pago = IF(Pago = true, false, true) where "
                + "cliente like " + venta.getNombreCli()
                + " and monto like " + venta.getMonto()
                + " and formaPago like" + venta.getFormaPago();
        Statement st = cn.createStatement();
        st.execute(sqlCambioEstadoPago);
        st.close();
        cn.close();
        
    }
     public static List<Venta> gananciaTotal(int mes) throws Exception{
            
        Connection cn = ConnectionManager.obtenerConexion();
        
            String sqlConsultaStock = "select "
                    + "(SELECT sum(monto)as monto from prenda as p "
                    + "inner join ventas as v "
                    + "on v.idprenda = p.idprenda where Month(fecha) = " + mes + ")"
                    + " - (select sum(costo)as costo from prenda as p "
                    + "inner join ventas as v "
                    + "on v.idprenda = p.idprenda where Month(fecha) = " + mes + ")"
                    + " as ganancia";
                   
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultaStock);
            Venta venta = new Venta();
            List<Venta> ventas = new ArrayList();
            while(rs.next()){
                int monto = rs.getInt("ganancia");
                System.out.println(monto);
                venta.setMonto(monto);
                
                ventas.add(venta);
            }                 
               
                
            st.close();
            cn.close();
         
    return ventas;
    }
}
