package dev.samstevens.drinkr.repository;

import dev.samstevens.drinkr.domain.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BreweryRepository extends JpaRepository<Brewery, Long> {
}
