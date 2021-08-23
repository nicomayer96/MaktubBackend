/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.dao;


import com.maktub.model.Stock;
import com.maktub.view.StockView;
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
    
                //Agregar al stock al prenda
    
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
    
            //Suma las cantidad en stock, en caso de que la prenda ya este cargada,
            //pero no borra el dato repetido
    String sqlSumaCantidades = "update stock set cantidad=(select sum(cantidad) "
            + "where idPrenda like (select idPrenda from prenda where "
            + "tipo = '" + stock.getTipo()
            + "' and talle = '" + stock.getTalle()
            + "' and marca = '" + stock.getMarca()
            + "' and color = '" + stock.getColor() + "' limit 1)) "
            + "where idPrenda like (select idPrenda from prenda where "
            + "tipo = '" + stock.getTipo()
            + "' and talle = '" + stock.getTalle()
            + "' and marca = '" + stock.getMarca()
            + "' and color = '" + stock.getColor() + "' limit 1)";
    
            //Eliminar stock repetido dejando el de mejor idStock
//    String sqlDeleteRepetido = "delete from stock where idPrenda like"
//            + " (select idPrenda from prenda where "
//            + "tipo = '" + stock.getTipo()
//            + "' and talle = '" + stock.getTalle()
//            + "' and marca = '" + stock.getMarca()
//            + "' and color = '" + stock.getColor() + "' limit 1) and "
//            + "idStock>(select min(idStock) from Stock where idPrenda like "
//            + "(select idPrenda from prenda where "
//            + "tipo = '" + stock.getTipo()
//            + "' and talle = '" + stock.getTalle()
//            + "' and marca = '" + stock.getMarca()
//            + "' and color = '" + stock.getColor() + "' limit 1))";
    Statement st = cn.createStatement();
    st.execute(sqlAgregarPrenda);
    st.execute(sqlAgregarStock);
    st.execute(sqlSumaCantidades);
//    st.execute(sqlDeleteRepetido);
    st.close();
    cn.close();
    }
    
            //metodo donde filtro es la columna de agrupamiento del select
    
    public static List<StockView> filtro() throws Exception{
        List <StockView> listaStock = new ArrayList();
        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsultaStock = "select p.tipo as tipo, p.talle as talle, p.marca as marca, p.color as color, p.costo as costo, s.cantidad as cantidad, s.idStock as idStock " +
                                    "from prenda as p " +
                                    "inner join stock as s " +
                                    "on p.idprenda = s.idPrenda Order by tipo, talle, marca, color";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultaStock);
            while(rs.next()){
                StockView stock = new StockView();
                String tipo = rs.getString("tipo");
                String talle = rs.getString("talle");
                String marca = rs.getString("marca");
                String color = rs.getString("color");
                int cant = rs.getInt("Cantidad");
                int costo = rs.getInt("costo");
                int idStock = rs.getInt("idStock");
                stock.setTipo(tipo);
                stock.setTalle(talle);
                stock.setMarca(marca);
                stock.setColor(color);
                stock.setCosto(costo);
                stock.setCantidad(cant);
                stock.setIdStock(idStock);
                listaStock.add(stock);
            }
            st.close();
            cn.close();
    
    return listaStock;
    }
    

    
    public static void eliminarStock(int idStock) throws Exception{
        Connection cn = ConnectionManager.obtenerConexion();

    String sqlEliminarStock = "delete from stock where idPrenda = " + idStock;
    Statement st = cn.createStatement();
    st.execute(sqlEliminarStock);
    st.close();
    cn.close();
    }
    
    
    }

