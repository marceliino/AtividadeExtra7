package br.com.fiap.dao;

import java.util.List;

public interface Dao<T> {
	
	void adicionar(T t);
	List<T> listar();
	void atualizar(T t);
	void remover(T t);
	T buscar(int id);
}
