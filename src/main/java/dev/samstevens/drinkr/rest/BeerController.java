package dev.samstevens.drinkr.rest;

import dev.samstevens.drinkr.domain.Beer;
import dev.samstevens.drinkr.domain.Brewery;
import dev.samstevens.drinkr.repository.BeerRepository;
import dev.samstevens.drinkr.repository.BreweryRepository;
import dev.samstevens.drinkr.rest.exception.BeerNotFoundException;
import dev.samstevens.drinkr.rest.exception.BreweryNotFoundException;
import dev.samstevens.drinkr.rest.request.BeerRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @TODO: PUT /beers/{id}
 * @TODO: GET /breweries/{breweryId}/beers (with pagination)
 */
@RestController
public class BeerController {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private BreweryRepository breweryRepository;

    @Autowired
    private BeerResourceAssembler resourceAssembler;

    @GetMapping("/beers/{id}")
    Resource<Beer> get(@PathVariable("id") Long id) {
        Beer beer = beerRepository.findById(id)
            .orElseThrow(() -> new BeerNotFoundException(id));

        return resourceAssembler.toResource(beer);
    }

    @PostMapping("/beers")
    ResponseEntity create(@Valid @RequestBody BeerRequestBody beerRequest) throws URISyntaxException {

        // Fetch the brewery from the ID provided in the request, or throw an exception
        Brewery brewery = breweryRepository.findById(beerRequest.getBreweryId())
                .orElseThrow(() -> new BreweryNotFoundException(beerRequest.getBreweryId()));

        // Create the new beer
        Beer beer = new Beer();
        beer.setName(beerRequest.getName());
        beer.setAbv(beerRequest.getAbv());
        beer.setType(beerRequest.getType());
        beer.setBrewery(brewery);

        // Save to the database and assemble the resource
        Resource<Beer> beerResource = resourceAssembler.toResource(beerRepository.save(beer));

        // Return a 201 created response with the resource
        return ResponseEntity
            .created(new URI(beerResource.getId().expand().getHref()))
            .body(beerResource);
    }

    @DeleteMapping("/beers/{id}")
    ResponseEntity delete(@PathVariable("id") Long id) {
        // Delete the beer
        beerRepository.deleteById(id);

        // Return an empty 204 response
        return ResponseEntity.noContent().build();
    }
}
