package com.scalefocus.java.simeonyachev.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalefocus.java.simeonyachev.beerservice.domain.Beer;
import com.scalefocus.java.simeonyachev.beerservice.repositories.BeerRepository;
import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDTO;
import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureRestDocs
@WebMvcTest(BeerController.class)
@ExtendWith(RestDocumentationExtension.class)
@ComponentScan(basePackages = "com.scalefocus.java.simeonyachev.beerservice.web.mappers")
class BeerControllerTest {

    private static final String BEER_URI = "/api/v1/beer/";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    BeerDTO beer;

    @BeforeEach
    void setUp() {
        beer = BeerDTO.builder()
                .name("Ariana")
                .style(BeerStyle.ALE)
                .price(new BigDecimal(12))
                .upc(1234L)
                .build();
    }

    @Test
    void getByIdSuccessfully() throws Exception {
        when(beerRepository.findById(any(UUID.class))).thenReturn(Optional.of(Beer.builder().build()));

        mockMvc.perform(get(BEER_URI + "{beerId}", UUID.randomUUID().toString())
                .param("iscold", "yes")
                .accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/beer-get",
                        pathParameters(parameterWithName("beerId").description("UUID of desired beer to get.")),
                        requestParameters(parameterWithName("iscold").description("Is Beer Cold Query param")),
                        responseFields(
                                fieldWithPath("id").description("Id of Beer"),
                                fieldWithPath("version").description("Version number"),
                                fieldWithPath("createdDate").description("Date Created"),
                                fieldWithPath("lastModifiedDate").description("Date Updated"),
                                fieldWithPath("name").description("Beer Name"),
                                fieldWithPath("style").description("Beer Style"),
                                fieldWithPath("upc").description("UPC of Beer"),
                                fieldWithPath("price").description("Price"),
                                fieldWithPath("quantityOnHand").description("Quantity On hand")
                        )));
    }

    @Test
    void saveBeerSuccessfully() throws Exception {
        String beerDTOJson = objectMapper.writeValueAsString(beer);

        ConstraintFields fields = new ConstraintFields(BeerDTO.class);

        mockMvc.perform(post(BEER_URI)
                .contentType(APPLICATION_JSON)
                .content(beerDTOJson))
                .andExpect(status().isCreated())
                .andDo(document("v1/beer-new",
                        requestFields(
                                fields.withPath("id").ignored(),
                                fields.withPath("version").ignored(),
                                fields.withPath("createdDate").ignored(),
                                fields.withPath("lastModifiedDate").ignored(),
                                fields.withPath("name").description("Name of the Beer"),
                                fields.withPath("style").description("Style of Beer"),
                                fields.withPath("upc").description("Beer UPC"),
                                fields.withPath("price").description("Beer Price"),
                                fields.withPath("quantityOnHand").ignored()
                        )));
    }

    @Test
    void updateBeerSuccessfully() throws Exception {
        String beerDTOJson = objectMapper.writeValueAsString(beer);

        mockMvc.perform(put(BEER_URI + UUID.randomUUID().toString())
                .contentType(APPLICATION_JSON)
                .content(beerDTOJson))
                .andExpect(status().isOk());
    }

    private static class ConstraintFields {
        private final ConstraintDescriptions constraintDescriptions;

        ConstraintFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints").value(StringUtils
                    .collectionToDelimitedString(this.constraintDescriptions
                            .descriptionsForProperty(path), ".")));
        }
    }
}