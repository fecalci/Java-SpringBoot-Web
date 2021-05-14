package com.sistemasactivos.pam.models.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.sistemasactivos.pam.models.entity.Transacciones;

public interface ITransaccionesService {
	
	public List<Transacciones> listarTodos();
	public void guardar(Transacciones transacciones);
	public Transacciones buscarPorId(int id);
	public void eliminar(int id);
	Page<Transacciones> paginacion(Integer paginaNum, Integer paginaTot);
	
}
