package com.api.parque.diversiones.dao;

import com.api.parque.diversiones.dto.UsuarioDto;
import com.api.parque.diversiones.models.Empleado;
import com.api.parque.diversiones.models.Persona;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class UsuarioDaoImp {
    @PersistenceContext
    EntityManager entityManager;
    String query = "";
    public int checkUser(UsuarioDto usuarioDto) {
        try{
            query="FROM Persona WHERE email = :email";
            Persona persona = entityManager.createQuery(query,Persona.class)
                    .setParameter("email",usuarioDto.getEmail())
                    .getSingleResult();
            Empleado empleado = entityManager.createQuery("FROM Empleado WHERE persona = :persona", Empleado.class)
                    .setParameter("persona", persona)
                    .getSingleResult();
            String passwordHashed = empleado.getPassword();
            Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            if(argon2.verify(passwordHashed,usuarioDto.getPassword())){
                return empleado.getRol();
            }
            return 10;
        }catch (Exception ex){
            return 0;
        }
    }

    public boolean setPassword(UsuarioDto usuarioDto) {
        try{
            Empleado empleado = entityManager.find(Empleado.class,usuarioDto.getIdEmpleado());
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1,1024,1,usuarioDto.getPassword());
            empleado.setPassword(hash);
            entityManager.merge(empleado);
            return true;
        }catch (Exception ex){
            return false;
        }

    }
}
