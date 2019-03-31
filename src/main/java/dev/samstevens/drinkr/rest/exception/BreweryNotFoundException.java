package dev.samstevens.drinkr.rest.exception;

public class BreweryNotFoundException extends RuntimeException {

    public BreweryNotFoundException(Long id) {
        super("Could not find employee with ID: " + id);
    }
}
