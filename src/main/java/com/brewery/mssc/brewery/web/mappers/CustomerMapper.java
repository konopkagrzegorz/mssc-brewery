package com.brewery.mssc.brewery.web.mappers;

import com.brewery.mssc.brewery.domain.Customer;
import com.brewery.mssc.brewery.web.model.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);
}
