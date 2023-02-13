package nascimento.patrick.restaurantsfinder.criterias.impl;

import java.util.stream.Stream;

import nascimento.patrick.restaurantsfinder.criterias.IFilterCriteria;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class NameCriteria implements IFilterCriteria<Restaurant> {

	@Override
	public Stream<Restaurant> filter(Stream<Restaurant> stream, Restaurant target) {
		return stream.filter(restaurant -> {
			return restaurant.getName().toLowerCase().contains(target.getName().toLowerCase());
		});
	}

	@Override
	public boolean accept(Restaurant target) {
		return null != target.getName() && !target.getName().isEmpty();
	}

}
