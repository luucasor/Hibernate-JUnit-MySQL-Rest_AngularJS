package br.com.luucasor.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Hibernate {

	private static EntityManagerFactory instanceFactory;

	public static EntityManager getEntityManager() {

		if(instanceFactory == null){
			instanceFactory = Persistence.createEntityManagerFactory("treasyPU");
		}

		return instanceFactory.createEntityManager();
	}
}
