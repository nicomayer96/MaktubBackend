/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.dao;


import com.maktub.model.Stock;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nico
 */
public class PrendaDao {
    
                //Agregar a la tabla prenda y si existe, al stock
    
    public static void agregarPrenda(Stock stock) throws Exception{
    Connection cn = ConnectionManager.obtenerConexion();
    String sqlAgregarPrenda = "insert into prenda(tipo, talle, marca, color, costo) " +
            "Values ( '" + stock.getTipo() + "', '"
            + stock.getTalle() + "', '"
            + stock.getMarca() + "', '"
            + stock.getColor() + "', "
            + stock.getCosto() + ")";
    String sqlAgregarStock = "insert into stock(idPrenda, cantidad) " +
            "values ((select idPrenda from prenda where "
            + "tipo = '" + stock.getTipo()
            + "' and talle = '" + stock.getTalle()
            + "' and marca = '" + stock.getMarca()
            + "' and color = '" + stock.getColor() + "' limit 1), "
            + stock.getCantidad() + ")";
    Statement st = cn.createStatement();
    st.execute(sqlAgregarPrenda);
    st.execute(sqlAgregarStock);
    st.close();
    cn.close();
    }
    
            //metodo donde filtro es la columna de agrupamiento del select
    
    public static List<Stock> filtro(String filtro) throws Exception{
        List <Stock> listaStock = new ArrayList();
        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsultaStock = "select p.tipo as tipo, p.talle as talle, p.marca as marca, p.color as color, p.costo as costo, s.cantidad as cantidad " +
                                    "from prenda as p " +
                                    "inner join stock as s " +
                                    "on p.idprenda = s.idPrenda Order by " + filtro;
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultaStock);
            while(rs.next()){
                Stock stock = new Stock();
                String tipo = rs.getString("tipo");
                String talle = rs.getString("talle");
                String marca = rs.getString("marca");
                String color = rs.getString("color");
                int cant = rs.getInt("Cantidad");
                int costo = rs.getInt("costo");
                stock.setTipo(tipo);
                stock.setTalle(talle);
                stock.setMarca(marca);
                stock.setColor(color);
                stock.setCosto(costo);
                stock.setCantidad(cant);
                listaStock.add(stock);
            }
            st.close();
            cn.close();
    
    return listaStock;
    }
    

    
    public static void eliminarStock(Stock stock) throws Exception{
        Connection cn = ConnectionManager.obtenerConexion();
    String sqlEliminarPrenda = "delete from prenda where idPrenda = " +
                                "(select IDprenda where tipo like '"
            + stock.getTipo()
            + "' and talle like '"+ stock.getTalle()
            + "' and marca like '" + stock.getMarca()
            + "' and color like '" + stock.getColor()
            + "')"; 
    String sqlEliminarStock = "delete from stock where idPrenda = " +
                                "(select IDprenda from prenda where tipo like '"
            + stock.getTipo()
            + "' and talle like '"+ stock.getTalle()
            + "' and marca like '" + stock.getMarca()
            + "' and color like '" + stock.getColor()
            + "')";
    Statement st = cn.createStatement();
    st.execute(sqlEliminarPrenda);
    st.execute(sqlEliminarStock);
    st.close();
    cn.close();
    }
    }

