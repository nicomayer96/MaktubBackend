/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.controller;

import com.maktub.dao.GastoDao;
import com.maktub.model.Gasto;
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

public class GastoController {
    
     @GetMapping("/verGastos")
    public ResponseEntity <List<Gasto>> readAll(@RequestParam() int mes) throws Exception{
        try{    
            List<Gasto> gastos = new ArrayList();
            gastos = GastoDao.verGastos(mes);

            return new ResponseEntity(gastos , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
     @PostMapping("/cargarGastos")
    public ResponseEntity create(@RequestBody Gasto gasto) throws Exception{
        try{            
            GastoDao.agregarGasto(gasto);
            return new ResponseEntity(gasto, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Error agregando gasto - " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
    }
}
