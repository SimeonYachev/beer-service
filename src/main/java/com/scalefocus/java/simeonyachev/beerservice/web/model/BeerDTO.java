package com.scalefocus.java.simeonyachev.beerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {
    private UUID id;
    private String name;
    private BeerStyle style;
    private BigDecimal price;
    private int version;
    private long upc;
    private int quantityOnHand;
    private OffsetDateTime createdDate;
    private OffsetDateTime lastModifiedDate;
}
