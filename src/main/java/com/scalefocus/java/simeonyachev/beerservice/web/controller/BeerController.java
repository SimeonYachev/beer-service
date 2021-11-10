package com.scalefocus.java.simeonyachev.beerservice.web.controller;

import com.scalefocus.java.simeonyachev.beerservice.services.BeerService;
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
    private final BeerService beerService;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getById(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(beerService.getById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> saveBeer(@Valid @RequestBody BeerDTO beerDTO) {
        return new ResponseEntity<>(beerService.saveNewBeer(beerDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTO> updateBeer(@PathVariable("beerId") UUID beerId,
                                              @Valid @RequestBody BeerDTO beerDTO) {
        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDTO), HttpStatus.OK);
    }
}
