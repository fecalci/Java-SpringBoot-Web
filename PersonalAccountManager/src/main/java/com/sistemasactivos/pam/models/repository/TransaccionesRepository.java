package com.sistemasactivos.pam.models.repository;
import org.springframework.data.repository.CrudRepository;

import com.sistemasactivos.pam.models.entity.Transacciones;

public interface TransaccionesRepository extends CrudRepository <Transacciones, Integer> {

}
