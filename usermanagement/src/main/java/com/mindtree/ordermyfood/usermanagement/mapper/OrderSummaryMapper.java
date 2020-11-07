package com.mindtree.ordermyfood.usermanagement.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.mindtree.ordermyfood.usermanagement.dto.OrderDetails;
import com.mindtree.ordermyfood.usermanagement.dto.OrderResponseDto;
import com.mindtree.ordermyfood.usermanagement.entity.OrderSummary;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface OrderSummaryMapper {

	@Mappings({		 
		@Mapping(target="restaurantId.resId",source="restaurantId"),	  	  
		@Mapping(target="customer.id",source="customerId")})
	OrderSummary orderDetailsToOrderSummary(OrderDetails orderDetails);

	@Mapping(target="customerId",source="customer.id")
	OrderResponseDto orderSummaryToOrderResponseDto(OrderSummary orderSummary);

	/*
	 * @Named("itemOrder") default String mapItem(OrderDetails orderDetails) {
	 * 
	 * List<Item> item = new ArrayList<Item>(); for(Integer i :
	 * orderDetails.getItems() )
	 * 
	 * item.get; }
	 */

	/*
	 * @Mapping(target = "param3List", expression =
	 * "java( source.getNestedList().stream().map(el -> el.getId()).collect(Collectors.toList()) )"
	 * ) protected abstract List mapStringtoList(List childSource);


     int mapItemToInt(Item item) {
         return item.getId();*/
}
