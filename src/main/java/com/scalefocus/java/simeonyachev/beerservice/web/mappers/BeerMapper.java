package com.scalefocus.java.simeonyachev.beerservice.web.mappers;

import com.scalefocus.java.simeonyachev.beerservice.domain.Beer;
import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);

    Beer beerDtoToBeer(BeerDto beerDTO);
}
