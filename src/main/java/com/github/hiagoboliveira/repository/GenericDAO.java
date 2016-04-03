package com.github.hiagoboliveira.repository;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class GenericDAO {

    @PersistenceContext(name = "PU")
    private EntityManager em;

    public <T> List<T> listAll(Class<T> entityClass) {

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        Root<T> variableRoot = query.from(entityClass);
        query.select(variableRoot);

        return em.createQuery(query).getResultList();
    }

    @Transactional
    public void add(Object object) {
        em.persist(object);
    }
}
