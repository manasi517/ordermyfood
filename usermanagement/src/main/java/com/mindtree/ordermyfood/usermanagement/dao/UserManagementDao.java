package com.mindtree.ordermyfood.usermanagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mindtree.ordermyfood.usermanagement.entity.Customer;

public interface UserManagementDao extends JpaRepository<Customer, Integer>{

	Customer saveAndFlush(Customer customer);
	
	Customer findById(int id);

	Customer findByMailId(String mailId);
}
