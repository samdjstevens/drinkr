package dev.samstevens.drinkr.rest;

import dev.samstevens.drinkr.domain.Brewery;
import dev.samstevens.drinkr.repository.BreweryRepository;
import dev.samstevens.drinkr.rest.exception.BreweryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

@RestController()
public class BreweryController {

    @Autowired
    private BreweryRepository breweryRepository;

    @Autowired
    private BreweryResourceAssembler resourceAssembler;

    @GetMapping("/breweries")
    Resources<Resource<Brewery>> all() {
        // Fetch all breweries and map to resources
        List<Resource<Brewery>> breweries = breweryRepository.findAll().stream()
                .map(resourceAssembler::toResource)
                .collect(Collectors.toList());

        // Return the brewery resources
        return new Resources<>(
            breweries,
            linkTo(methodOn(this.getClass()).all()).withSelfRel()
        );
    }

    @PostMapping("/breweries")
    ResponseEntity create(@RequestBody Brewery brewery) throws URISyntaxException {
        // Save the brewery and create a resource
        Resource<Brewery> breweryResource = resourceAssembler.toResource(breweryRepository.save(brewery));

        // Return a 201 response with the resource
        return ResponseEntity
                .created(new URI(breweryResource.getId().expand().getHref()))
                .body(breweryResource);
    }

    @GetMapping("/breweries/{id}")
    Resource<Brewery> get(@PathVariable("id") Long id) {
        // Find the brewery or throw an exception
        Brewery brewery = breweryRepository.findById(id)
                .orElseThrow(() -> new BreweryNotFoundException(id));

        // Return a brewery resource
        return resourceAssembler.toResource(brewery);
    }

    @PutMapping("/breweries/{id}")
    Resource<Brewery> update(@PathVariable("id") Long id, @RequestBody Brewery newBrewery) {
        // Find the brewery or throw an exception
        Brewery brewery = breweryRepository.findById(id)
                .orElseThrow(() -> new BreweryNotFoundException(id));

        brewery.setName(newBrewery.getName());
        brewery.setCity(newBrewery.getCity());
        breweryRepository.save(brewery);

        // Return the brewery resource
        return resourceAssembler.toResource(brewery);
    }

    @DeleteMapping("/breweries/{id}")
    ResponseEntity delete(@PathVariable("id") Long id) {
        // Delete the brewery
        breweryRepository.deleteById(id);

        // Return a 204 response
        return ResponseEntity.noContent().build();
    }
}
