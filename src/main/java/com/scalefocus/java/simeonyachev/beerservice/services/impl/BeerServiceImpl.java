package com.scalefocus.java.simeonyachev.beerservice.services.impl;

import com.scalefocus.java.simeonyachev.beerservice.domain.Beer;
import com.scalefocus.java.simeonyachev.beerservice.repositories.BeerRepository;
import com.scalefocus.java.simeonyachev.beerservice.services.BeerService;
import com.scalefocus.java.simeonyachev.beerservice.web.controller.NotFoundException;
import com.scalefocus.java.simeonyachev.beerservice.web.mappers.BeerMapper;
import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return beerMapper.beerToBeerDto(beerRepository.save(beerMapper.beerDtoToBeer(beerDto)));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setName(beerDto.getName());
        beer.setStyle(beerDto.getStyle().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }
}
