package nascimento.patrick.restaurantsfinder.criterias.impl;

import java.util.stream.Stream;

import nascimento.patrick.restaurantsfinder.criterias.IFilterCriteria;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class DistanceCriteria implements IFilterCriteria<Restaurant> {
	
	private final static Integer MINIMUM_DISTANCE = 1;
	private final static Integer MAXIMUM_DISTANCE = 10;

	@Override
	public Stream<Restaurant> filter(Stream<Restaurant> stream, Restaurant target) {
		return stream.filter(r -> r.getDistance() <= target.getDistance());
	}

	@Override
	public boolean accept(Restaurant target) {
		Integer distance = target.getDistance();
		
		return null != distance  && distance >= MINIMUM_DISTANCE && distance <= MAXIMUM_DISTANCE;
	}

}
