package com.mindtree.ordermyfood.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.ordermyfood.usermanagement.entity.OrderSummary;

public interface OrderManagementDao extends JpaRepository<OrderSummary, Integer>{

	OrderSummary saveAndFlush(OrderSummary orderDetails);
}
