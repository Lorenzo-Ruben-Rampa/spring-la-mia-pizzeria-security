package org.lessons.java.pizza_security.repository;
import org.lessons.java.pizza_security.model.SpecialOffer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Integer> {
	
}

