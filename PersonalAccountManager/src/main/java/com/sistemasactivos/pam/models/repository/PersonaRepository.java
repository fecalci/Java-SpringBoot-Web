package com.sistemasactivos.pam.models.repository;

import org.springframework.data.repository.CrudRepository;


import com.sistemasactivos.pam.models.entity.Persona;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {

}
