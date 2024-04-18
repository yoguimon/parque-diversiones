package com.api.parque.diversiones.dto;

import com.api.parque.diversiones.models.Persona;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmpleadoDto {
    private int idEmpleado;
    private Persona persona;
    private String dni;
    private String direccion;
    private LocalDate fechaNacimiento;
    private String celular;
    private byte rol;

    public EmpleadoDto() {
    }

    public EmpleadoDto(Persona persona, String dni, String direccion, LocalDate fechaNacimiento, String celular, byte rol) {
        this.persona = persona;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.celular = celular;
        this.rol = rol;
    }

    public EmpleadoDto(int idEmpleado, Persona persona, String dni, String direccion, LocalDate fechaNacimiento, String celular, byte rol) {
        this.idEmpleado = idEmpleado;
        this.persona = persona;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.celular = celular;
        this.rol = rol;
    }
}
