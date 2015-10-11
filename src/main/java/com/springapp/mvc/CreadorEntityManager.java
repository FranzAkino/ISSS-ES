package com.springapp.mvc;

import com.persistencia.CirujiaJpaController;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by akino on 05-21-15.
 */
public class CreadorEntityManager {

    public static EntityManagerFactory crearEMF(){
        return Persistence.createEntityManagerFactory("NewPersistenceUnit");
    }

    public static CirujiaJpaController cirujiaController(){
        return new CirujiaJpaController(crearEMF());
    }



}
