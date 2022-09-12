package com.windula.demoetl.service;

import com.windula.demoetl.dao.BeerDao;
import com.windula.demoetl.model.Beer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeerServiceImpl implements BeerService{

    @Autowired
    private BeerDao beerDao;

    @Override
    public void addBeer(Beer beer) {
        beerDao.addBeer(beer);
    }
}
