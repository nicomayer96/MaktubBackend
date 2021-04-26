/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.controller;



import com.maktub.dao.VentaDao;
import com.maktub.model.Venta;
import com.maktub.view.VentaView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nico
 */
@RestController
@RequestMapping
@CrossOrigin(origins= "*")
public class VentaController {
    
    
    
    @GetMapping("/ventas")
    public ResponseEntity <List<Venta>> readAll(@RequestParam(value="mes") int mes) throws Exception{
        try{    
            List<Venta> ventas = new ArrayList();
            ventas = VentaDao.verVentas(mes);
            return new ResponseEntity(ventas , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    
    
    @PostMapping("/cargarVenta")
    public ResponseEntity create(@RequestBody VentaView ventaView) throws Exception{
        try{            
            Venta v = new Venta(
                    ventaView.getTipo(),
                    ventaView.getTalle(),
                    ventaView.getMarca(),
                    ventaView.getColor(),
                    ventaView.getNombreCli(),
                    ventaView.getMonto(),
                    ventaView.isEstadoPago(),                    
                    ventaView.getFormaPago(),
                    ventaView.getFecha(),
                    ventaView.getEnvio());
                    
            VentaDao.agregarVenta(v);  
            return new ResponseEntity(v, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Error agregando venta - " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
    }
    
}
