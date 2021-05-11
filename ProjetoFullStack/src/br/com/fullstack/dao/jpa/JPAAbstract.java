package br.com.fullstack.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public abstract class JPAAbstract <T> extends JPAConnection{

	public boolean salvar (T t) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.persist(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}

	public boolean atualizar (T t) {
		EntityManager em = getEntityManager();
		em.getTransaction().begin();
		em.merge(t);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> buscar() {
		String jpql = "select c from "+getEntityName()+ " c";
		Query query = super.getQuery(jpql);
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();

		List<T> listObjetos = new ArrayList<T>();
		for (Object object: list) {			
			listObjetos.add((T) object);
		}
		super.close();
		return listObjetos;
	}


	@SuppressWarnings("unchecked")
	public T buscarPorId(int id) {
		String jpql = "select c from "+getEntityName()+" c where c.id = " + id;
		Query query = super.getQuery(jpql);
		@SuppressWarnings("rawtypes")
		List list = query.getResultList();
		for (Object object: list){
			return ((T) object);
		}
		super.close();
		return null;
	}

	public boolean excluirPorId(int id) {
		try {
			EntityManager em = getEntityManager();
			em.getTransaction().begin();
			Query query = em.createQuery("DELETE "+getEntityName()+" c where c.id =:id ");
			query.setParameter("id", id);
			query.executeUpdate();
			em.getTransaction().commit();
			em.close();
			return true;
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}

	}

	public abstract String getEntityName();
}
