package com.api.parque.diversiones.dao;

import com.api.parque.diversiones.dto.EmpleadoDto;
import com.api.parque.diversiones.models.Empleado;
import com.api.parque.diversiones.models.Persona;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.EmptyStackException;
import java.util.List;
import java.util.Optional;
@Repository
@Transactional
public class EmpleadoDaoImp implements CrudDao<Empleado>{
    @PersistenceContext
    EntityManager entityManager;
    String query = "";
    @Override
    public List<Empleado> getAll() {
        query = "SELECT e FROM Empleado e INNER JOIN e.persona p WHERE p.estado = 1";
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public boolean delete(int id) {//id persona
        try{
            Persona persona = entityManager.find(Persona.class,id);
            persona.setEstado((byte)0);
            if(persona!=null)return true;
            else return false;
        }catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean create(Empleado empleado) {
        return false;
    }
    public boolean addEmpleado(EmpleadoDto empleadoDto){
        try{
            Persona persona = empleadoDto.getPersona();
            persona.setEstado((byte)1);
            entityManager.persist(persona);
            Empleado empleado = new Empleado();
            empleado.setPersona(persona);
            empleado.setDni(empleadoDto.getDni());
            empleado.setDireccion(empleadoDto.getDireccion());
            empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
            empleado.setCelular(empleadoDto.getCelular());

            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(1,1024,1,empleadoDto.getDni());
            empleado.setPassword(hash);

            empleado.setRol(empleadoDto.getRol());
            entityManager.persist(empleado);
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    @Override
    public Empleado getOne(int id) {
        try{
            Empleado empleado = entityManager.find(Empleado.class,id);
            return empleado;
        }catch(Exception ex){
            return null;
        }
    }

    @Override
    public boolean setOne(Empleado empleado) {
        return true;
    }
    public boolean update(EmpleadoDto empleadoDto){
        try{
            Empleado empleado = entityManager.find(Empleado.class,empleadoDto.getIdEmpleado());

            empleado.getPersona().setNombre(empleadoDto.getPersona().getNombre());
            empleado.getPersona().setApellido(empleadoDto.getPersona().getApellido());
            empleado.getPersona().setEmail(empleadoDto.getPersona().getEmail());
            empleado.setDni(empleadoDto.getDni());
            empleado.setDireccion(empleadoDto.getDireccion());
            empleado.setFechaNacimiento(empleadoDto.getFechaNacimiento());
            empleado.setCelular(empleadoDto.getCelular());
            empleado.setRol(empleado.getRol());
            entityManager.merge(empleado);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }
    public void prueba(EmpleadoDto empleadoDto){
        System.out.println("AQUIIIIII");
        Empleado empleado = entityManager.find(Empleado.class,empleadoDto.getIdEmpleado());
        System.out.println(empleado.getPersona().getNombre());
        System.out.println(empleado.getDni());

    }
}
