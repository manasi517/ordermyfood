package com.mindtree.ordermyfood.usermanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindtree.ordermyfood.usermanagement.entity.Item;

@Repository
public interface ItemManagementDao extends JpaRepository<Item, Integer> {

	List<Item> findAllByIdIn(List<Integer> itemList);
}
