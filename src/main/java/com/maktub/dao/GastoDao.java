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

    String sqlAgregarGasto = "insert into Gastos(Descripcion, monto, fecha) " +
            "values ( " + gasto.getDescripcion() + ", " + 
             gasto.getMonto() + ", "
            + gasto.getFecha() + ")";

    Statement st = cn.createStatement();
    st.execute(sqlAgregarGasto);

    st.close();
    cn.close();
    }
    
    
    public static List<Gasto> verGastos(int mes, int year) throws Exception{
        List <Gasto> gastos = new ArrayList();

        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsultaGastos = "select Descripcion, Monto, Fecha " +
                                "from Gastos " +
                                "where month(fecha) = " + mes +
                                " and year(fecha) = " + year;
            
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultaGastos);
            
            while(rs.next()){
                Gasto gasto = new Gasto();
                String descripcion = rs.getString("descripcion");
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
}
