package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.util.JpaUtil;

public class GenericDao<T> implements Dao<T> {
	
	private final Class<T> clazz;
	protected EntityManager em;
	
	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}

	@Override
	public void adicionar(T t) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> listar() {
		em = JpaUtil.getEntityManager();
		return em.createQuery("From " + clazz.getSimpleName()).getResultList();
	}

	@Override
	public void atualizar(T t) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public void remover(T t) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(em.merge(t));
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public T buscar(int id) {
		em = JpaUtil.getEntityManager();
		em.getTransaction().begin();
		T t = em.find(clazz, id);
		em.close();
		return t;
	}
}