package com.api.parque.diversiones.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private int idEmpleado;
    private String email;
    private String password;

    public UsuarioDto() {
    }

    public UsuarioDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UsuarioDto(int idEmpleado, String password) {
        this.idEmpleado = idEmpleado;
        this.password = password;
    }

    @Override
    public String toString() {
        return "UsuarioDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
