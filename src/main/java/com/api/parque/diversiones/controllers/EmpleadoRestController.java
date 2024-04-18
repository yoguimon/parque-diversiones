package com.api.parque.diversiones.controllers;

import com.api.parque.diversiones.dao.EmpleadoDaoImp;
import com.api.parque.diversiones.dto.EmpleadoDto;
import com.api.parque.diversiones.models.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class EmpleadoRestController {
    @Autowired
    private EmpleadoDaoImp empleadoDaoImp;
    @GetMapping("/empleados")
    public List<Empleado> getEmpleados(){
        return empleadoDaoImp.getAll();
    }
    @GetMapping("/empleados/{id}")
    public ResponseEntity<?> getEmpleado(@PathVariable int id){//id del empleado
        Empleado empleado = empleadoDaoImp.getOne(id);
        if(empleado!=null){
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/empleados")
    public ResponseEntity<String> addEmpleado(@RequestBody EmpleadoDto empleadoDto){
        //falta hacer pruebasssss
        if(empleadoDaoImp.addEmpleado(empleadoDto)){
            return ResponseEntity.status(HttpStatus.CREATED).body("empleado agregado con exito");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error!! no se pudo agregar el empleado");
    }
    @PutMapping("/empleados")
    public ResponseEntity setEmpleado(@RequestBody EmpleadoDto empleadoDto){
        if (empleadoDaoImp.update(empleadoDto)) {
            return ResponseEntity.ok().build(); // Devuelve 200 OK si la actualización fue exitosa
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Devuelve 500 Internal Server Error si hubo un problema al actualizar
        }
    }
    @DeleteMapping("/empleados/{id}")
    public ResponseEntity deleteEmpleado(@PathVariable int id){
        boolean deleted = empleadoDaoImp.delete(id);
        if (deleted) {
            return ResponseEntity.ok().build(); // Empleado eliminado con éxito
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // No se encontró el empleado con el ID dado
        }
    }
    //PRUEBASSS
    @PutMapping("/empleados/prueba")
    public void prueba(@RequestBody EmpleadoDto empleadoDto){
        empleadoDaoImp.prueba(empleadoDto);
    }
}
