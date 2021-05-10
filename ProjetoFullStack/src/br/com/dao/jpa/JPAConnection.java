package br.com.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JPAConnection {
	private EntityManagerFactory conexao;
	
	private EntityManagerFactory conectar() {
		try {
			if ( conexao != null && conexao.isOpen() ) {
				return conexao;
			}
		} catch (Exception e) {}
		
		conexao = Persistence.createEntityManagerFactory("desafio");
		return conexao;
		//EntityManager em = conexao.createEntityManager();
	}
	
	// este m�todo ser� o nosso createdStatement
	protected EntityManager getEntityManager() {
		return conectar().createEntityManager();
	}
	
	// este m�todo ser� o nosso prepareStatement
	protected Query getQuery(String jpql) {
		
		return this.getEntityManager().createQuery(jpql);
	}

	protected void close() {
		this.conexao.close();
	}
	

}
