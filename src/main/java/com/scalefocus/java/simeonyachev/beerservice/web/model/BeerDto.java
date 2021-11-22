package com.scalefocus.java.simeonyachev.beerservice.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape.STRING;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

    @Null
    private UUID id;
    @Null
    private Integer version;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = STRING)
    private OffsetDateTime createdDate;
    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = STRING)
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String name;

    @NotNull
    private BeerStyle style;

    @NotNull
    @Positive
    @JsonFormat(shape = STRING)
    private BigDecimal price;

    @NotNull
    private String upc;

    @Positive
    private Integer quantityOnHand;
}
