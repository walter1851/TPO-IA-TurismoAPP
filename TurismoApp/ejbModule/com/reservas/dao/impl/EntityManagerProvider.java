package com.reservas.dao.impl;

import javax.persistence.EntityManager;

public class EntityManagerProvider {
	public EntityManager getEntityManager() {
		return DaoFactory.createEntityManager();
	}

}
