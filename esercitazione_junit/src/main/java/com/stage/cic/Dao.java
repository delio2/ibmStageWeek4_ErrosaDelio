package com.stage.cic;

import javax.persistence.EntityManager;

import com.stage.cic.entity.Apartment;
import com.stage.cic.util.JpaUtil;

public class Dao {
	
	public Apartment findApartment(int apartmentId) {
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		Apartment apartment = null;
		try {
			apartment = entityManager.find(Apartment.class, apartmentId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return apartment;
	}
	
	public Apartment updateApartment(Apartment apartment) {
		EntityManager entityManager = JpaUtil.getEntityManagerFactory().createEntityManager();
		Apartment updatedApartment = null;
		try {
			entityManager.getTransaction().begin();
			updatedApartment = entityManager.merge(apartment);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			entityManager.close(); 
		}
		return updatedApartment;
	}

}
