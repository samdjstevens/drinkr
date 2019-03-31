package dev.samstevens.drinkr.rest.request;

import dev.samstevens.drinkr.domain.Beer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BeerRequestBody {

    @NotEmpty
    private String name;

    @NotNull
    private Long breweryId;

    @NotNull
    private Beer.Type type;

    @NotNull
    private Double abv;

    private String imageUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBreweryId() {
        return breweryId;
    }

    public void setBreweryId(Long breweryId) {
        this.breweryId = breweryId;
    }

    public Beer.Type getType() {
        return type;
    }

    public void setType(Beer.Type type) {
        this.type = type;
    }

    public Double getAbv() {
        return abv;
    }

    public void setAbv(Double abv) {
        this.abv = abv;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
