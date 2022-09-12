package com.windula.demoetl.dao;

import com.windula.demoetl.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Repository
public class BeerDaoImpl implements BeerDao{

    @Autowired
    @Qualifier("demoetlEntityManager")
    private EntityManager entityManager;

    @Override
    @Transactional("demoetlTransactionManager")
    public void addBeer(Beer beer) {
        entityManager.persist(beer);
    }
}
