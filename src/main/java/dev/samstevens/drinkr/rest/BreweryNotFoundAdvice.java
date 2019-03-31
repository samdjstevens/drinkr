package dev.samstevens.drinkr.rest;

import dev.samstevens.drinkr.rest.exception.BreweryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Handle BreweryNotFoundException exceptions thrown in controllers.
 */
@ControllerAdvice
public class BreweryNotFoundAdvice {

    @ExceptionHandler(BreweryNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String breweryNotFoundHandler(BreweryNotFoundException ex) {
        return ex.getMessage();
    }
}
