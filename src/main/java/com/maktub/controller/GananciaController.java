/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.controller;

import com.maktub.dao.GananciaDao;
import com.maktub.model.Ganancia;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nico
 */
@RestController
@RequestMapping("/ganancia")
@CrossOrigin(origins= "*")
public class GananciaController {
    
    @GetMapping("/verGanancia")
    public ResponseEntity ganancia(@RequestParam()int mes, int year) throws Exception{
        try{   
            System.out.println(year + " ganancia");
            int ganancia = GananciaDao.gananciaTotal(mes, year);
            return new ResponseEntity(ganancia , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
