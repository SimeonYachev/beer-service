package com.scalefocus.java.simeonyachev.beerservice.repositories;

import com.scalefocus.java.simeonyachev.beerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {
}
