package com.api.parque.diversiones.controllers;

import com.api.parque.diversiones.dao.EmpleadoDaoImp;
import com.api.parque.diversiones.dao.UsuarioDaoImp;
import com.api.parque.diversiones.dto.EmpleadoDto;
import com.api.parque.diversiones.dto.UsuarioDto;
import com.api.parque.diversiones.models.Empleado;
import com.api.parque.diversiones.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class UsuarioRestController {
    @Autowired
    private UsuarioDaoImp usuarioDaoImp;
    @PostMapping("/usuarios")
    public ResponseEntity<Integer> signIn(@RequestBody UsuarioDto usuarioDto){
        int rol = usuarioDaoImp.checkUser(usuarioDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(rol);

    }
    @PostMapping("/usuarios/password")
    public ResponseEntity<String> changePassword(@RequestBody UsuarioDto usuarioDto){
        if(usuarioDaoImp.setPassword(usuarioDto)){
            return ResponseEntity.status(HttpStatus.CREATED).body("se cambio el password con exito");
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error!! no se pudo cambiar password del empleado");
    }
}
