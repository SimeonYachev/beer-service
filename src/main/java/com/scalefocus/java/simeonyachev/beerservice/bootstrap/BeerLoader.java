package com.scalefocus.java.simeonyachev.beerservice.bootstrap;

import com.scalefocus.java.simeonyachev.beerservice.domain.Beer;
import com.scalefocus.java.simeonyachev.beerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder()
                    .name("Ariana")
                    .style("Tumno")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(4832362L)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .name("Zagorka")
                    .style("Svetlo")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(13214L)
                    .price(new BigDecimal("11.95"))
                    .build());
        }
    }
}
