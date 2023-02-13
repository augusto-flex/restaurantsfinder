package nascimento.patrick.restaurantsfinder.criterias.impl;

import java.util.stream.Stream;

import nascimento.patrick.restaurantsfinder.criterias.IFilterCriteria;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class CustomerRatingCriteria implements IFilterCriteria<Restaurant> {
	
	private final static Integer MINIMUM_CUSTOMER_RATING = 1;
	private final static Integer MAXIMUM_CUSTOMER_RATING = 5;

	@Override
	public Stream<Restaurant> filter(Stream<Restaurant> stream, Restaurant target) {
		return stream.filter(r -> r.getCustomerRating() >= target.getCustomerRating());
	}

	@Override
	public boolean accept(Restaurant target) {
		Integer customerRating = target.getCustomerRating();
		
		return null != customerRating && customerRating >= MINIMUM_CUSTOMER_RATING && customerRating <= MAXIMUM_CUSTOMER_RATING;
	}

}
