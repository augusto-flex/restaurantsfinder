package nascimento.patrick.restaurantsfinder.criterias.impl;

import java.util.stream.Stream;

import nascimento.patrick.restaurantsfinder.criterias.IFilterCriteria;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class CuisineCriteria implements IFilterCriteria<Restaurant> {

	@Override
	public Stream<Restaurant> filter(Stream<Restaurant> stream, Restaurant target) {
		
		return stream.filter(restaurant -> {
			String targetLowerCase = target.getCuisine().getName().toLowerCase();
			return restaurant.getCuisine().getName().toLowerCase().contains(targetLowerCase);
		});
	}

	@Override
	public boolean accept(Restaurant target) {
		return  null != target.getCuisine() && !target.getCuisine().getName().isEmpty();
	}

}
