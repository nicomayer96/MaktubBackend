/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.dao;

import com.maktub.model.Gasto;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Nico
 */
public class GastoDao {
    
    public static void agregarGasto(Gasto gasto) throws Exception{
    Connection cn = ConnectionManager.obtenerConexion();
    String dateString = String.format("%1$tY-%1$tm-%1$td", gasto.getFecha());
    
    String sqlAgregarGasto = "insert into Gastos(Descripcion, monto, fecha) " +
            "values ( '" + gasto.getDescripcion() + "', " + 
             gasto.getMonto() + ", '"
            + dateString + "')";

    Statement st = cn.createStatement();
    st.execute(sqlAgregarGasto);

    st.close();
    cn.close();
    }
    
    
    public static List<Gasto> verGastos(int mes) throws Exception{
        List <Gasto> gastos = new ArrayList();

        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsultaGastos = "select Descripcion, Monto, Fecha " +
                                "from Gastos " +
                                "where month(fecha) = " + mes +
                                " and year(fecha) = 2021";
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultaGastos);
            
            while(rs.next()){
                Gasto gasto = new Gasto();
                String descripcion = rs.getString("Descripcion");
                int monto = rs.getInt("Monto");
                Date fecha = new Date();
                fecha = rs.getDate("fecha");

                gasto.setDescripcion(descripcion);
                gasto.setMonto(monto);
                gasto.setFecha(fecha);
                
                gastos.add(gasto);
            }
            st.close();
            cn.close();
    
    return gastos;
}
    
    public static int verMontoGastos(int mes) throws Exception{
        int gastoTotal = 0;

        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsultaGastos = "select sum(Monto) as monto " +
                                "from Gastos " +
                                "where month(fecha) = " + mes +
                                " and year(fecha) = 2021";
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultaGastos);
            
            while(rs.next()){
                
                gastoTotal = rs.getInt("Monto");

            }
            st.close();
            cn.close();
    
    return gastoTotal;
}
}
