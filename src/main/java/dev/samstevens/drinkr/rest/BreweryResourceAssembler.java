package dev.samstevens.drinkr.rest;

import dev.samstevens.drinkr.domain.Brewery;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@Component
public class BreweryResourceAssembler implements ResourceAssembler<Brewery, Resource<Brewery>> {

    @Override
    public Resource<Brewery> toResource(Brewery brewery) {
        return new Resource<>(
            brewery,
            linkTo(methodOn(BreweryController.class).get(brewery.getId())).withSelfRel(),
            linkTo(methodOn(BreweryController.class).all()).withRel("breweries")
        );
    }
}
