package com.mindtree.ordermyfood.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.ordermyfood.entity.Item;

public interface ItemRepository  extends JpaRepository<Item, Integer>{

}
