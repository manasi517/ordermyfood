package com.mindtree.ordermyfood.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ordermyfood.entity.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Integer>{

	
}
