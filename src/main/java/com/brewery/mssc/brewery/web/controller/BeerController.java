package com.brewery.mssc.brewery.web.controller;

import com.brewery.mssc.brewery.services.BeerService;
import com.brewery.mssc.brewery.web.model.BeerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@Validated
public class BeerController {

    private final BeerService beerService;

    @Autowired
    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDTO> getBeer(@PathVariable @NotNull String  beerId) {
        return new ResponseEntity<>(beerService.getBeerByID(UUID.fromString(beerId)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createBeer(@RequestBody @Valid BeerDTO beerDTO) {
        BeerDTO saved = beerService.saveNewBeer(beerDTO);
        HttpHeaders header = new HttpHeaders();
        header.add("Location", "/api/v1/beer/" + saved.getId().toString());
        return new ResponseEntity(header,HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable String beerId, @Valid @RequestBody BeerDTO beerDTO) {
        beerService.updateBeer(UUID.fromString(beerId),beerDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeer(@PathVariable String beerId) {
        beerService.deleteBeer(UUID.fromString(beerId));
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<List<String>> errors(ConstraintViolationException e) {
//        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
//
//        e.getConstraintViolations().stream()
//                .forEach(constraintViolation -> errors.add(constraintViolation.getPropertyPath()
//                        + " : " + constraintViolation.getMessage()));
//
//        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
//    }
}
