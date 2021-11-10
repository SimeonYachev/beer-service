package com.scalefocus.java.simeonyachev.beerservice.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDTO {

    @Null
    private UUID id;
    @Null
    private Integer version;

    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @NotNull
    private BeerStyle style;

    @NotNull
    @Positive
    private BigDecimal price;

    @NotNull
    @Positive
    private Long upc;

    @Positive
    private Integer quantityOnHand;
}
