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
    
    
    String sqlAgregar = "insert into ventas (cliente, monto, formaPago, fecha, envio, idPrenda, numeroVenta) " +
            "Values ( '" + venta.getNombreCli() + "', "
            + venta.getMonto() + ", '"
            + venta.getFormaPago() + "', '"
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
            String sqlConsultaVentas = "select v.Cliente, v.monto, v.formaPago, v.fecha, v.envio, v.numeroVenta,"
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
     
     // sql traer tipo 
//        "SELECT DISTINCT tipo FROM `prenda` order by tipo"
     
     //sql traer marca segun tipo 
//        "SELECT DISTINCT marca FROM `prenda` where tipo LIKE "TIPO" order by marca"
     
     //sql traer talle segun tipo y marca 
//        "SELECT DISTINCT talle FROM `prenda` where tipo LIKE "TIPO" and marca LIKE "MARCA" order by talle"
     
     //sql traer color segun tipo, marca y talle
//        "SELECT color FROM `prenda` where tipo LIKE "TIPO" and marca LIKE "MARCA" and talle LIKE "TALLE" order by talle"
     
         public static List<String> traerTipo() throws Exception{
        List <String> lista = new ArrayList();
        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsulta = "select DISTINCT p.tipo as tipo, s.cantidad as cantidad"
                    + " from prenda as p inner join stock as s on p.idprenda = s.idPrenda"
                    + " where cantidad > 0 Order by tipo";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsulta);
            while(rs.next()){
                String tipo = rs.getString("tipo");
                lista.add(tipo);
            }
            st.close();
            cn.close();
    
    return lista;
    }
                  public static List<String> traerMarca(String tipo) throws Exception{
        List <String> lista = new ArrayList();
        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsulta = "select DISTINCT p.marca as marca"
                    + " from prenda as p inner join stock as s "
                    + "on p.idprenda = s.idPrenda "
                    + "where s.cantidad > 0 and p.tipo like '" + tipo + "' Order by marca";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsulta);
            while(rs.next()){
                String marca = rs.getString("marca");
                lista.add(marca);
            }
            st.close();
            cn.close();
    
    return lista;
    }
                    public static List<String> traerTalle(String tipo, String marca) throws Exception{
        List <String> lista = new ArrayList();
        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsulta = "select DISTINCT p.talle as talle"
                    + " from prenda as p inner join stock as s "
                    + "on p.idprenda = s.idPrenda "
                    + "where s.cantidad > 0 and p.tipo like '" + tipo + "' and p.marca like '" + marca + "' Order by talle";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsulta);
            while(rs.next()){
                String talle = rs.getString("talle");
                lista.add(talle);
            }
            st.close();
            cn.close();
    
    return lista;
    }      
                    public static List<String> traerColor(String tipo, String marca, String talle) throws Exception{
        List <String> lista = new ArrayList();
        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsulta = "select DISTINCT p.color as color"
                    + " from prenda as p inner join stock as s "
                    + "on p.idprenda = s.idPrenda "
                    + "where s.cantidad > 0 and p.tipo like '" + tipo + "' and"
                    + " p.marca like '" + marca + "' and p.talle like '" + talle + "' Order by color";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsulta);
            while(rs.next()){
                String color = rs.getString("color");
                lista.add(color);
            }
            st.close();
            cn.close();
    
    return lista;
    }    
}
