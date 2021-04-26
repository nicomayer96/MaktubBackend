/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.dao;

import com.maktub.model.Login;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author Nico
 */
public class LoginDao {
    
    public static Login consulta() throws Exception{
            Login l = new Login();
        Connection cn = ConnectionManager.obtenerConexion();
            String sqlConsultaStock = "Select * from login";
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sqlConsultaStock);
            
            while(rs.next()){
                String user = rs.getString("usuario");
                String password = rs.getString("contraseña");
                l.setUser(user);
                l.setPassword(password);
                }
            st.close();
            cn.close();
    
    return l;
    }
}
