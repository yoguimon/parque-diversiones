package com.api.parque.diversiones.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="persona")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persona {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idPersona")
    private int idPersona;
    @Column(name="nombre")
    private String nombre;
    @Column(name="apellido")
    private String apellido;
    @Column(name="email")
    private String email;
    @Column(name="estado")
    private byte estado;

    public Persona() {
    }

    public Persona(int idPersona, String nombre, String apellido, String email, byte estado) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.estado = estado;
    }

    public Persona(String nombre, String apellido, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
    }

}
