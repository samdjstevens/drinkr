package dev.samstevens.drinkr.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
public class Beer {
    enum Type { IPA, PALE_ALE, STOUT, PORTER, SOUR, OTHER }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Brewery brewery;

    @Column
    @NotEmpty
    private String name;

    @Column
    @NotEmpty
    private Type type;

    @Column
    @NotEmpty
    private double abv;

    @Column
    private String imageUrl;

    @Column
    private LocalDate createdAt;

    @Column
    private LocalDate updatedAt;

    public Long getId() {
        return id;
    }

    public Brewery getBrewery() {
        return brewery;
    }

    public void setBrewery(Brewery brewery) {
        this.brewery = brewery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getAbv() {
        return abv;
    }

    public void setAbv(double abv) {
        this.abv = abv;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
