/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.maktub.controller;



import com.maktub.dao.PrendaDao;
import com.maktub.dao.VentaDao;
import com.maktub.model.Venta;
import com.maktub.view.VentaView;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public ResponseEntity <List<Venta>> readAll(@RequestParam() int mes, int year) throws Exception{
        try{    
            List<Venta> ventas = new ArrayList();  
            ventas = VentaDao.verVentas(mes, year);  

            return new ResponseEntity(ventas , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @GetMapping("/gananciaTotal")
    public ResponseEntity GananciaMensual(@RequestParam() int mes, int year) throws Exception{
        try{    
            //List<Venta> ventas = new ArrayList();
            int ganancia = VentaDao.gananciaTotal(mes, year);
            //ventas = VentaDao.gananciaTotal(mes);
            return new ResponseEntity(ganancia , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/tipo")
    public ResponseEntity<List<String>>traerTipo() throws Exception{
        try{    
            List<String> tipos = new ArrayList();
            
            tipos = VentaDao.traerTipo();
            return new ResponseEntity(tipos , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/marca")
    public ResponseEntity<List<String>>traerMarca(@RequestParam(value="tipo") String tipo) throws Exception{
        try{    
            List<String> marcas = new ArrayList();
            
            marcas = VentaDao.traerMarca(tipo);
            return new ResponseEntity(marcas , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/talle")
    public ResponseEntity<List<String>>traerTalle(@RequestParam() String tipo, String marca) throws Exception{
        try{    
            List<String> talles = new ArrayList();
            
            talles = VentaDao.traerTalle(tipo, marca);
            return new ResponseEntity(talles , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("/color")
    public ResponseEntity<List<String>>traerColor(@RequestParam() String tipo, String marca, String talle) throws Exception{
        try{    
            List<String> colores = new ArrayList();
            
            colores = VentaDao.traerColor(tipo, marca, talle);
            return new ResponseEntity(colores , HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/cargarVentas")
    public ResponseEntity create(@RequestBody VentaView ventaView) throws Exception{
        try{            
            Venta v = new Venta(
                    ventaView.getTipo(),
                    ventaView.getTalle(),
                    ventaView.getMarca(),
                    ventaView.getColor(),
                    ventaView.getNombreCli(),
                    ventaView.getMonto(),                    
                    ventaView.getFormaPago(),
                    ventaView.getFecha(),
                    ventaView.getEnvio());
                    
            VentaDao.agregarVenta(v);  
            return new ResponseEntity(v, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity("Error agregando venta - " + e, HttpStatus.INTERNAL_SERVER_ERROR);
        
    }
    }
  
    @DeleteMapping(value="/delete/{numVenta}")
    public ResponseEntity delete(@PathVariable("numVenta") int numVenta) throws Exception{
        try{     
            PrendaDao.eliminarStock(numVenta);
            
            return new ResponseEntity(numVenta + " " + HttpStatus.OK, HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity(e + " " + HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
//    @PutMapping("/{cliente}")
//    public ResponseEntity <Venta> updateVenta(@PathVariable String cliente, @RequestBody Venta venta ) throws Exception{
//        try{
//           VentaDao.cambioEstadoPago(venta);
//        return new ResponseEntity(venta, HttpStatus.OK);
//        }catch(Exception e){
//            return new ResponseEntity("Error en modificacion - " + e, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    @PutMapping ( " / employee / {id} " )
// public  ResponseEntity < Employee > updateEmployee ( @PathVariable ( value  =  " id " ) Long employeeId,
//   @Valid  @RequestBody  Employee employeeDetails) lanza ResourceNotFoundException {
//  Employee employee = employeeRepository . findById (employeeId)
// .orElseThrow (() - >  new  ResourceNotFoundException ( " Empleado no encontrado para este id :: "  + employeeId));
//
// empleado . setEmailId (employeeDetails . getEmailId ());
// empleado . setLastName (empleadoDetails . getLastName ());
// empleado . setFirstName (empleadoDetails . getFirstName ());
// empleado final  updatedEmployee = employeeRepository . guardar (empleado);
// devuelve ResponseEntity . ok (empleado actualizado); 
}


