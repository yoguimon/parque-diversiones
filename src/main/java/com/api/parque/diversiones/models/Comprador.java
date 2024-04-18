package com.api.parque.diversiones.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="comprador")
public class Comprador {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="idComprador")
    private int idComprador;
    @OneToOne(fetch = FetchType.EAGER)
    //@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true) // Un usuario se asocia con un empleado
    @JoinColumn(name = "idPersona", referencedColumnName = "idPersona")
    private Persona persona;
}
