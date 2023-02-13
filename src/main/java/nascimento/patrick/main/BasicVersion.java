package nascimento.patrick.main;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nascimento.patrick.restaurantsfinder.csv.CSVLoader;
import nascimento.patrick.restaurantsfinder.model.Cuisine;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class BasicVersion {

	public static void main(String[] args) {


		List<Restaurant> restaurants = CSVLoader.getRestaurants();

		Restaurant target = new Restaurant();
		target.setCustomerRating(null);
		target.setDistance(null);
		target.setPrice(null);
		target.setName("Tasty");

		target.setCuisine(new Cuisine(11, "Spanish"));

		log(restaurants);
		Stream<Restaurant> stream = restaurants.stream();

		if (null != target.getCustomerRating()) {
			stream = stream.filter(r -> r.getCustomerRating() >= target.getCustomerRating());
		}

		if (null != target.getDistance()) {
			stream = stream.filter(r -> r.getDistance() <= target.getDistance());
		}

		if (null != target.getPrice()) {
			stream = stream.filter(r -> r.getPrice() <= target.getPrice());
		}

		if (null != target.getName()) {
			stream = stream.filter(r -> r.getName().contains(target.getName()));
		}

		if (null != target.getCuisine()) {
			stream = stream.filter(r -> r.getCuisine().getName().contains(target.getCuisine().getName()));
		}

		List<Restaurant> ALLRESULT = stream.limit(5).collect(Collectors.toList());
		System.out.println("final result");
		log(ALLRESULT);

	}

	private static void log(List<Restaurant> rr) {
		for(Restaurant r : rr)
		System.out.println(r);
	}

}
