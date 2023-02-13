package nascimento.patrick.restaurantsfinder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nascimento.patrick.restaurantsfinder.criterias.IFilterCriteria;
import nascimento.patrick.restaurantsfinder.criterias.impl.CuisineCriteria;
import nascimento.patrick.restaurantsfinder.criterias.impl.CustomerRatingCriteria;
import nascimento.patrick.restaurantsfinder.criterias.impl.DistanceCriteria;
import nascimento.patrick.restaurantsfinder.criterias.impl.NameCriteria;
import nascimento.patrick.restaurantsfinder.criterias.impl.PriceCriteria;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class RestaurantsFinder {
	
	private static List<IFilterCriteria<Restaurant>> criterias;
	private static final int MAXIMUM_VALUES = 5;
	private List<Restaurant> allRestaurants;
	
	static {
		criterias = new ArrayList<>();

		criterias.add(new CustomerRatingCriteria());
		criterias.add(new DistanceCriteria());
		criterias.add(new PriceCriteria());
		criterias.add(new NameCriteria());
		criterias.add(new CuisineCriteria());
	}
	
	public RestaurantsFinder(List<Restaurant> allRestaurants) {
		this.allRestaurants = allRestaurants;
	}
	
	public List<Restaurant> find( Restaurant target){
		
		Stream<Restaurant> stream = allRestaurants.stream();
		
		for (IFilterCriteria<Restaurant> criteria: criterias) {
		
			if(criteria.accept(target)) {
				stream = criteria.filter(stream, target);
			}
		};
		
		return stream.sorted(new BestRestaurantsComparator()) //
				.limit(MAXIMUM_VALUES) //
				.collect(Collectors.toList());
	}

}
