/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.controller;

import com.maktub.dao.LoginDao;
import com.maktub.model.Login;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nico
 */
@RestController
@RequestMapping("/login")
@CrossOrigin(origins= "https://files.000webhost.com")
public class LoginController {
    
    @GetMapping
    public ResponseEntity <Login> readAll() throws Exception{
        try{          
            Login l = LoginDao.consulta();
            return new ResponseEntity(l , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Error en consulta ----" + e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
