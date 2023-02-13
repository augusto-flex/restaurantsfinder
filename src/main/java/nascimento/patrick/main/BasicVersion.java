package nascimento.patrick.main;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import nascimento.patrick.restaurantsfinder.csv.CSVLoader;
import nascimento.patrick.restaurantsfinder.model.Cuisine;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class BasicVersion {

	public static void main(String[] args) {

		// A Restaurant Name match is defined as an exact or partial String match with
		// what users provided. For example, “Mcd” would match “Mcdonald’s”.
		// A Customer Rating match is defined as a Customer Rating equal to or more than
		// what users have asked for. For example, “3” would match all the 3 stars
		// restaurants plus all the 4 stars and 5 stars restaurants.
		// A Distance match is defined as a Distance equal to or less than what users
		// have asked for. For example, “2” would match any distance that is equal to or
		// less than 2 miles from your company.
		// A Price match is defined as a Price equal to or less than what users have
		// asked for. For example, “15” would match any price that is equal to or less
		// than $15 per person.
		// A Cuisine match is defined as an exact or partial String match with what
		// users provided. For example, “Chi” would match “Chinese”. You can find all
		// the possible Cuisines in the cuisines.csv file. You can assume each
		// restaurant offers only one cuisine.

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
