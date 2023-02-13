package nascimento.patrick.restaurantsfinder.criterias.impl;

import java.util.stream.Stream;

import nascimento.patrick.restaurantsfinder.criterias.IFilterCriteria;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class PriceCriteria implements IFilterCriteria<Restaurant> {
	
	private final static Integer MINIMUM_PRICE = 10;
	private final static Integer MAXIMUM_PRICE = 50;

	@Override
	public Stream<Restaurant> filter(Stream<Restaurant> stream, Restaurant target) {
		return stream.filter(restaurant -> restaurant.getPrice() <= target.getPrice());
	}

	public boolean accept(Restaurant target) {
		Integer price = target.getPrice();
		
		return null != price && price >= MINIMUM_PRICE && price <= MAXIMUM_PRICE;
	}

}
