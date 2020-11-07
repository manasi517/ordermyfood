package com.mindtree.ordermyfood.sevice;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mindtree.ordermyfood.Repository.ItemRepository;
import com.mindtree.ordermyfood.Repository.LocationRepository;
import com.mindtree.ordermyfood.Repository.OfferRepository;
import com.mindtree.ordermyfood.Repository.OrderMyFoodRepository;
import com.mindtree.ordermyfood.Repository.PhotosRepository;
import com.mindtree.ordermyfood.Repository.ReviewRepository;
import com.mindtree.ordermyfood.dto.RequestDto;
import com.mindtree.ordermyfood.dto.RestaurantDto;
import com.mindtree.ordermyfood.entity.Item;
import com.mindtree.ordermyfood.entity.LocationEntity;
import com.mindtree.ordermyfood.entity.Offer;
import com.mindtree.ordermyfood.entity.Photos;
import com.mindtree.ordermyfood.entity.Restaurants;
import com.mindtree.ordermyfood.entity.Reviews;
import com.mindtree.ordermyfood.mapper.RestaurantMapper;

@Service
public class OrderMyFoodServiceImpl implements OrderMyFoodService {

	@Autowired
	OrderMyFoodRepository foodRepo;

	@Autowired
	LocationRepository locationRepo;
	
	@Autowired
	PhotosRepository photoRepo;
	
	@Autowired
	ItemRepository itemRepo;
	
	@Autowired
	ReviewRepository reviewRepo;

	@Autowired
	OfferRepository offerRepo;
	
	 @Autowired RestaurantMapper restaurantMapper;
	 

	@Override
	public RequestDto loadRestuarantsDetails() {

		final String url ="https://developers.zomato.com/api/v2.1/search?count=20&lat=12.929415&lon=77.626613";
		URI uri = null;
		try {
			uri = new URI(url);
			//Log.d("URI created: " + uri);
		}
		catch (URISyntaxException e) {
			// Log.e("URI Syntax Error: " + e.getMessage());
		}
		HttpHeaders headers = new HttpHeaders();
		headers.set("user-key", "f056cba22dbfab9dca5e890742a3a0fa");

		RestTemplate template = new RestTemplate();
		HttpEntity entity=new HttpEntity(headers);

		/*
		 * ObjectMapper mapper = new ObjectMapper();
		 * mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
		 * mapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
		 * MappingJackson2HttpMessageConverter converter = new
		 * MappingJackson2HttpMessageConverter(); converter.setObjectMapper(mapper);
		 * template.getMessageConverters().add(0, converter);
		 */

		//	ResponseEntity<RequestDto> response = template.exchange(url, HttpMethod.GET, entity, RequestDto.class, param);
		ResponseEntity<RequestDto> response = template.exchange(uri,  HttpMethod.GET, entity, RequestDto.class);
		//	ResponseEntity<RequestDto> response = template.get
		RequestDto restaurants = response.getBody();

		//foodRepo.saveAndFlush(entity);

		if(restaurants!=null) {
		
		for(RestaurantDto restaurantDto : restaurants.getRestaurants())
		{
			//Restaurant restaurantEntity = restaurantMapper.restaurantDtoToRestaurant(restaurantDto);

			LocationEntity location=new LocationEntity(restaurantDto.getLocation().getLatitude()
					,restaurantDto.getLocation().getLongitude(),restaurantDto.getLocation().getAddress(),restaurantDto.getLocation().getZipcode());
			location=locationRepo.saveAndFlush(location);

			Restaurants restaurantEntity = new Restaurants(restaurantDto.getId(),restaurantDto.getName(),
					restaurantDto.getUser_rating(),restaurantDto.getThumb(),restaurantDto.getAverage_cost_for_two(),location,restaurantDto.getPhotos_url());
			restaurantEntity = foodRepo.saveAndFlush(restaurantEntity);
			
			if(restaurantDto.getPhotos()!=null ) {
			for(com.mindtree.ordermyfood.dto.Photos p:restaurantDto.getPhotos())
			{
				Photos photos = restaurantMapper.photosDtoToPhotoEntity(p);
				photos.setRestaurant(restaurantEntity);
				photoRepo.saveAndFlush(photos);
			}
			}
					
			loadItemDetails(restaurantEntity);
			loadReviews(restaurantEntity);
			loadOffers(restaurantEntity);
		}
		}
		return restaurants;

	}


	private void loadOffers(Restaurants restaurantEntity) {
		Offer offer= new Offer();
		offer.setOfferCode("RES20");
		offer.setOfferType("Restaurant");
		offer.setDescription("Get 20% off on each order!");
		offer.setRestaurnt(restaurantEntity);
		offerRepo.saveAndFlush(offer);
	}


	@Override
	public void loadItemDetails(Restaurants restaurantEntity) {
		
		Item item = new Item();
		item.setName("Kaju curry");
		item.setPrice((int)(Math.random() * (200 - 50 + 1) + 50));
		item.setType("Indian");
		item.setVegFlag('V');
		item.setRestaurant(restaurantEntity);
		itemRepo.saveAndFlush(item);
		
		Item item2 = new Item();
		item2.setName("Thai Chicken tikka");
		item2.setPrice((int)(Math.random() * (200 - 50 + 1) + 50));
		item2.setType("Thai");
		item2.setVegFlag('N');
		item2.setRestaurant(restaurantEntity);
		itemRepo.saveAndFlush(item2);
		
		Item item3 = new Item();
		item3.setName("Noodles");
		item3.setPrice((int)(Math.random() * (200 - 50 + 1) + 50));
		item3.setType("Chinese");
		item3.setVegFlag('V');
		item3.setRestaurant(restaurantEntity);
		itemRepo.saveAndFlush(item3);
		
	}


	public void loadReviews(Restaurants restaurantEntity) {
		
		Reviews review=new Reviews();
		review.setLikes(0);
		review.setRating(3.5);
		review.setRating_text("good");
		review.setRestaurant(restaurantEntity);
		review.setReview_time_friendly("3 months ago");
		review.setUserName("Elina");
		reviewRepo.saveAndFlush(review);
		
		Reviews review1=new Reviews();
		review1.setLikes(5);
		review1.setRating(4);
		review1.setRating_text("very good");
		review1.setRestaurant(restaurantEntity);
		review1.setReview_time_friendly("2 months ago");
		review1.setUserName("Stefan");
		reviewRepo.saveAndFlush(review1);
		
		
		Reviews review2=new Reviews();
		review2.setLikes(10);
		review2.setRating(4.5);
		review2.setRating_text("exellent");
		review2.setRestaurant(restaurantEntity);
		review2.setReview_time_friendly("8 months ago");
		review2.setUserName("Manc");
		reviewRepo.saveAndFlush(review2);
	}


}
