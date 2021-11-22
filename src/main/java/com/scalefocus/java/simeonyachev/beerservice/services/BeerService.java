package com.scalefocus.java.simeonyachev.beerservice.services;

import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId);

    BeerDto saveNewBeer(BeerDto beerDTO);

    BeerDto updateBeer(UUID beerId, BeerDto beerDTO);
}
