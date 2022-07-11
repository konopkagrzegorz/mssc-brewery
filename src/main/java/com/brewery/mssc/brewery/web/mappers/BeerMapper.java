package com.brewery.mssc.brewery.web.mappers;

import com.brewery.mssc.brewery.domain.Beer;
import com.brewery.mssc.brewery.web.model.BeerDTO;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDTO beerToBeerDTO(Beer beer);

    Beer beerDTOToBeer(BeerDTO beerDTO);
}
