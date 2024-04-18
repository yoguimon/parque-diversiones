package com.api.parque.diversiones.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name="empleado")
public class Empleado {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idEmpleado")
    private int idEmpleado;
    @OneToOne(fetch = FetchType.EAGER)
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // Un usuario se asocia con un empleado
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private Persona persona;
    @Column(name="dni")
    private String dni;
    @Column(name="direccion")
    private String direccion;
    @Column(name="fechaNacimiento")
    private LocalDate fechaNacimiento;
    @Column(name="celular")
    private String celular;
    @Column(name="password")
    private String password;
    @Column(name="rol")
    private byte rol;

    public Empleado() {
    }

    public Empleado(int idEmpleado, Persona persona, String dni, String direccion, LocalDate fechaNacimiento, String celular, String password, byte rol) {
        this.idEmpleado = idEmpleado;
        this.persona = persona;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.celular = celular;
        this.password = password;
        this.rol = rol;
    }

    public Empleado(Persona persona, String dni, String direccion, LocalDate fechaNacimiento, String celular) {
        this.persona = persona;
        this.dni = dni;
        this.direccion = direccion;
        this.fechaNacimiento = fechaNacimiento;
        this.celular = celular;
    }
}
