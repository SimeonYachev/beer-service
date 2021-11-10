package com.scalefocus.java.simeonyachev.beerservice.web.controller;

import com.scalefocus.java.simeonyachev.beerservice.repositories.BeerRepository;
import com.scalefocus.java.simeonyachev.beerservice.web.mappers.BeerMapper;
import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@RequiredArgsConstructor
public class BeerController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getById(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerMapper.beerToBeerDTO(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveBeer(@Valid @RequestBody BeerDTO beerDTO) {
        beerRepository.save(beerMapper.beerDTOToBeer(beerDTO));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId,
                                              @Valid @RequestBody BeerDTO beerDTO) {
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setName(beerDTO.getName());
            beer.setStyle(beerDTO.getStyle().name());
            beer.setPrice(beerDTO.getPrice());
            beer.setUpc(beerDTO.getUpc());

            beerRepository.save(beer);
        });
        return new ResponseEntity(HttpStatus.OK);
    }
}
