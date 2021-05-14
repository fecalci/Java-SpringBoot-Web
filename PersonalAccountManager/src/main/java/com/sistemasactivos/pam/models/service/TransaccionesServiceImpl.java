package com.sistemasactivos.pam.models.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.sistemasactivos.pam.models.entity.Transacciones;
import com.sistemasactivos.pam.models.repository.TransaccionesRepository;

@Service
public class TransaccionesServiceImpl implements ITransaccionesService {

	@Autowired
	private TransaccionesRepository TransaccionesRepository;
	
	@Override
	public List<Transacciones> listarTodos() {		
		return (List<Transacciones>) TransaccionesRepository.findAll();
	}
	

	@Override
	public void guardar(Transacciones transacciones) {
		TransaccionesRepository.save(transacciones);
		
	}

	@Override
	public Transacciones buscarPorId(int id) {
		return TransaccionesRepository.findById(id).orElse(null);
	}

	@Override
	public void eliminar(int id) {
		TransaccionesRepository.deleteById(id);
		
	}


	@Override
	public Page<Transacciones> paginacion(Integer paginaNum, Integer paginaTot) {
		
		Pageable pag = PageRequest.of(paginaNum - 1, paginaTot);
		
		return this.TransaccionesRepository.findAll(pag);
	}
	
	

}
