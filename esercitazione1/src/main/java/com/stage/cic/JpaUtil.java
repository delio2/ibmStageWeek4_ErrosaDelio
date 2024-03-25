package com.stage.cic;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	
    private static EntityManagerFactory emf = buildEntityManagerFactory();
    
    private static EntityManagerFactory buildEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("esercitazione1");
    }

    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }
	
}