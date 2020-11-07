package com.mindtree.ordermyfood.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.ordermyfood.entity.LocationEntity;

public interface LocationRepository  extends JpaRepository<LocationEntity, Integer>{

	public LocationEntity saveAndFlush(LocationEntity location);
}
