package com.sistemasactivos.pam.models.service;

import java.util.List;

import com.sistemasactivos.pam.models.entity.Transacciones;

public interface ITransaccionesService {
	
	public List<Transacciones> listarTodos();
	public void guardar(Transacciones transacciones);
	public Transacciones buscarPorId(int id);
	public void eliminar(int id);
	
}
