package com.mindtree.ordermyfood.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.ordermyfood.entity.Reviews;

public interface ReviewRepository extends JpaRepository<Reviews, Integer>{

}
