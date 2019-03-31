package dev.samstevens.drinkr.rest.exception;

public class BeerNotFoundException extends RuntimeException {

    public BeerNotFoundException(Long id) {
        super("Could not find beer with ID: " + id);
    }
}
