package com.springapp.mvc;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by akino on 05-21-15.
 */
public class CreadorEntityManager {
    public static EntityManagerFactory crearEMF(){
        return Persistence.createEntityManagerFactory("NewPersistenceUnit");
    }
}
