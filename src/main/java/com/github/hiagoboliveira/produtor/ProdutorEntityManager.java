package com.github.hiagoboliveira.produtor;


import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class ProdutorEntityManager {

    @PersistenceContext(name = "PU")
    private EntityManager em;

    @Produces
    public EntityManager createEntityManager() {
        return em;
    }

}
