package com.scalefocus.java.simeonyachev.beerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.scalefocus.java.simeonyachev.beerservice.web.model.BeerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;


import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

    private static final String BEER_URI = "/api/v1/beer/";

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getByIdSuccessfully() throws Exception {
        mockMvc.perform(get(BEER_URI + UUID.randomUUID().toString()))
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveBeerSuccessfully() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

        mockMvc.perform(post(BEER_URI)
                .contentType(APPLICATION_JSON)
                .content(beerDTOJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeerSuccessfully() throws Exception {
        BeerDTO beerDTO = BeerDTO.builder().build();
        String beerDTOJson = objectMapper.writeValueAsString(beerDTO);

        mockMvc.perform(put(BEER_URI + UUID.randomUUID().toString())
                .contentType(APPLICATION_JSON)
                .content(beerDTOJson))
                .andExpect(status().isOk());
    }
}