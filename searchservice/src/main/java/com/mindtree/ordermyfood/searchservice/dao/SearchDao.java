package com.mindtree.ordermyfood.searchservice.dao;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindtree.ordermyfood.searchservice.entity.Restaurants;

@Repository
public interface SearchDao extends JpaRepository<Restaurants, Integer> {

	@Cacheable("restaurants")
	@Query("select distinct r from Restaurants r, LocationEntity l, Item i " +
			"where (i.restaurant=r.resId) "+ "and (l.id=r.location) " +
			"and (:veg is null or i.vegFlag = :veg) " +
			"and (:type is null or i.type = :type) " +
			"and (:budget is null or r.budget <= :budget) " +
			"and (:rating is null or r.rating >= :rating) " +
			"and ((:search is null or r.name LIKE concat('%',ifnull(:search,''),'%')) " +
			"or (:search is null or i.type LIKE concat('%',ifnull(:search,''),'%')))" +
			"and (6371 * acos( cos( radians(:lat) ) * cos( radians( l.latitude ) ) "+
			" * cos( radians(l.longitude ) - radians(:lng) ) + sin( radians(:lat) ) * "
			+ "sin(radians(l.latitude)) ) <= :dist)")	
	List<Restaurants> findRestaurants(
			@Param("dist") Double distance, @Param("lat") Double lat,
			@Param("lng") Double lng, @Param("type") String foodtype, 
			@Param("budget") Double budget, @Param("rating") Double rating,
			@Param("search") String searchkeyword, @Param("veg") Character veg);

	@CachePut(value="restaurants")
	@Query("select distinct r from Restaurants r, LocationEntity l, Item i " +
			"where (i.restaurant=r.resId) "+ "and (l.id=r.location) " +
			"and (:veg is null or i.vegFlag = :veg) " +
			"and (:type is null or i.type = :type) " +
			"and (:budget is null or r.budget <= :budget) " +
			"and (:rating is null or r.rating >= :rating) " +
			"and ((:search is null or r.name LIKE concat('%',ifnull(:search,''),'%')) " +
			"or (:search is null or i.type LIKE concat('%',ifnull(:search,''),'%')))" )	
	List<Restaurants> findRestaurants( 
			@Param("type") String foodtype, @Param("budget") Double budget, 
			@Param("rating") Double rating, @Param("search") String searchkeyword, 
			@Param("veg") Character veg);

}
