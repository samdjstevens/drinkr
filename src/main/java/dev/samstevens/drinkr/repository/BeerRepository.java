package dev.samstevens.drinkr.repository;

import dev.samstevens.drinkr.domain.Beer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BeerRepository extends JpaRepository<Beer, Long> {
}