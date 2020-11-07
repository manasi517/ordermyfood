package com.mindtree.ordermyfood.ordermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.mindtree.ordermyfood.ordermanagement.entity.Item;
import com.mindtree.ordermyfood.ordermanagement.entity.Restaurant;

@Component
public interface ItemDAO extends JpaRepository<Item, Integer>{


	List<Item> findItemByRestaurant(Restaurant restaurantId);
}
