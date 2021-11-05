package com.scalefocus.java.simeonyachev.beerservice.web.controller;

import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getById(@PathVariable("beerId") UUID beerId) {
        return new ResponseEntity<>(BeerDTO.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDTO> saveBeer(@RequestBody BeerDTO beerDTO) {
        return new ResponseEntity<>(beerDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<BeerDTO> updateBeer(@PathVariable("beerId") UUID beerId,
                                              @RequestBody BeerDTO beerDTO) {
        return new ResponseEntity<>(beerDTO, HttpStatus.OK);
    }
}
