package br.com.pedidos.jdbc;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class JpaFactory {
	private static EntityManagerFactory emf = Persistence			
			.createEntityManagerFactory("pedidos");

	public EntityManager getEntityManager() {
		return emf.createEntityManager();
	}

	public void close(JpaFactory em) {
		em.close(em);
	}

}
