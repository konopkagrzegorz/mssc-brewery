package com.brewery.mssc.brewery.services;

import com.brewery.mssc.brewery.web.model.BeerDTO;
import com.brewery.mssc.brewery.web.model.BeerStyle;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDTO getBeerByID(UUID beerId) {
        return BeerDTO.builder().id(UUID.randomUUID()).beerName("Galaxy Cat").beerStyle(BeerStyle.PILSNER).upc(31234200036L).build();
    }

    @Override
    public BeerDTO saveNewBeer(BeerDTO beerDTO) {
        return BeerDTO.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateBeer(UUID beerId, BeerDTO beerDTO) {

    }

    @Override
    public void deleteBeer(UUID beerId) {

    }
}
