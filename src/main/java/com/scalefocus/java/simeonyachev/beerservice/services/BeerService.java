package com.scalefocus.java.simeonyachev.beerservice.services;

import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDTO;

import java.util.UUID;

public interface BeerService {
    BeerDTO getById(UUID beerId);

    BeerDTO saveNewBeer(BeerDTO beerDTO);

    BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO);
}
