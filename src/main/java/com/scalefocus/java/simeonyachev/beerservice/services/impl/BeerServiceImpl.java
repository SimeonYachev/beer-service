package com.scalefocus.java.simeonyachev.beerservice.services.impl;

import com.scalefocus.java.simeonyachev.beerservice.domain.Beer;
import com.scalefocus.java.simeonyachev.beerservice.repositories.BeerRepository;
import com.scalefocus.java.simeonyachev.beerservice.services.BeerService;
import com.scalefocus.java.simeonyachev.beerservice.web.controller.NotFoundException;
import com.scalefocus.java.simeonyachev.beerservice.web.mappers.BeerMapper;
import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BeerServiceImpl implements BeerService {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @Override
    public BeerDTO getById(UUID beerId) {
        return beerMapper.beerToBeerDTO(beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        return beerMapper.beerToBeerDTO(beerRepository.save(beerMapper.beerDTOToBeer(beerDTO)));
    }

    @Override
    public BeerDTO updateBeer(UUID beerId, BeerDTO beerDTO) {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setName(beerDTO.getName());
        beer.setStyle(beerDTO.getStyle().name());
        beer.setPrice(beerDTO.getPrice());
        beer.setUpc(beerDTO.getUpc());

        return beerMapper.beerToBeerDTO(beerRepository.save(beer));
    }
}
