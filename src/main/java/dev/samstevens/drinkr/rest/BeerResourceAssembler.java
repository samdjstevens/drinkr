package dev.samstevens.drinkr.rest;

import dev.samstevens.drinkr.domain.Beer;
import dev.samstevens.drinkr.domain.Brewery;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class BeerResourceAssembler implements ResourceAssembler<Beer, Resource<Beer>> {

    @Override
    public Resource<Beer> toResource(Beer beer) {
        return new Resource<>(
            beer,
            linkTo(methodOn(BeerController.class).get(beer.getId())).withSelfRel()
        );
    }
}
