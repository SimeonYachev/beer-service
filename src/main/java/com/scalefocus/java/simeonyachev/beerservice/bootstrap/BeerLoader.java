package com.scalefocus.java.simeonyachev.beerservice.bootstrap;

import com.scalefocus.java.simeonyachev.beerservice.domain.Beer;
import com.scalefocus.java.simeonyachev.beerservice.repositories.BeerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_1_UPC = "0631";
    public static final String BEER_2_UPC = "0632";
    public static final String BEER_3_UPC = "0633";

    private final BeerRepository beerRepository;

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
                    .upc(BEER_1_UPC)
                    .price(new BigDecimal("12.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .name("Zagorka")
                    .style("Svetlo")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_2_UPC)
                    .price(new BigDecimal("11.95"))
                    .build());

            beerRepository.save(Beer.builder()
                    .name("Burgasko")
                    .style("Svetlo")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(BEER_3_UPC)
                    .price(new BigDecimal("10.95"))
                    .build());
        }
    }
}
