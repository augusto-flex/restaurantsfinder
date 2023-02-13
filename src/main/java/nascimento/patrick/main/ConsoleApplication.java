package nascimento.patrick.main;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

import nascimento.patrick.restaurantsfinder.RestaurantsFinder;
import nascimento.patrick.restaurantsfinder.csv.CSVLoader;
import nascimento.patrick.restaurantsfinder.model.Cuisine;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class ConsoleApplication {

	public static void main(String[] args) {

		RestaurantsFinder finder = new RestaurantsFinder(CSVLoader.getRestaurants());

		boolean consoleApplication = args.length == 0;

		if (consoleApplication) {
			startConsoleApplication(finder);
		} else {
			startSingleSearch(finder, args);
		}
	}

	private static void startSingleSearch(RestaurantsFinder finder, String[] args) {
		if (args.length == 5) {
			String name = StringUtils.isEmpty(args[0]) ? null : args[0];
			String cuisine = StringUtils.isEmpty(args[1]) ? null : args[1];
			Integer distance = StringUtils.isEmpty(args[2]) ? null : Integer.parseInt(args[2]);
			Integer customerRating = StringUtils.isEmpty(args[3]) ? null : Integer.parseInt(args[3]);
			Integer price = StringUtils.isEmpty(args[4]) ? null : Integer.parseInt(args[4]);

			Restaurant target = new Restaurant();
			target.setName(name);
			target.setCuisine(cuisine != null ? new Cuisine(0, cuisine) : null);
			target.setDistance(distance);
			target.setCustomerRating(customerRating);
			target.setPrice(price);

			List<Restaurant> restaurants = finder.find(target);

			printResults(restaurants);

		} else {
			System.out.println("The system expects 5 arguments, each separated with comas.");

		}

	}

	private static void startConsoleApplication(RestaurantsFinder finder) {
		int option = 0;
		try (Scanner scanner = new Scanner(System.in)) {
			while (option != 4) {

				System.out.println("Welcome to Restaurants Finder! ");
				System.out.println("");
				System.out.println("  type your option: 		");
				System.out.println("  1 - find a restaurant		");
				System.out.println("  2 - list all restaurants	");
				System.out.println("  3 - list all cuisines		");
				System.out.println("  4 - exit application		");
				System.out.println("");

				option = scanner.nextInt();
				scanner.nextLine();

				switch (option) {
				case 1:
					findRestaurantMenu(scanner, finder);
					break;
				case 2:
					System.out.println("Here is a list of all restaurants: ");
					printRestaurants(CSVLoader.getRestaurants());
					break;
				case 3:
					System.out.println("Here is a list of all cuisines: ");
					printCuisines(CSVLoader.getCuisines());

					break;
				case 4:
					// end of application
					break;

				default:
					System.out.println("Invalid option, try again.");
				}
				System.out.println("");
				System.out.println("-------------------------------------------");
				System.out.println("");
			}
		}

	}

	private static void findRestaurantMenu(Scanner scanner, RestaurantsFinder finder) {
		System.out.println("");

		System.out.println("Type the restaurant name (or nothing if desired): ");
		String name = readString(scanner);

		System.out.println("Type the cuisine option (or nothing if desired): ");
		String cuisine = readString(scanner);

		System.out.println("Type the maximum distance, between 1 and 10 (or nothing if desired): ");
		Integer distance = readInteger(scanner);

		System.out.println("Type the customer rating, between 1 and 5 (or nothing if desired): ");
		Integer customerRating = readInteger(scanner);

		System.out.println("Type the price, between 10 and 50 (or nothing if desired): ");
		Integer price = readInteger(scanner);

		System.out.println("");
		Restaurant target = new Restaurant();
		target.setName(name);
		target.setCuisine(cuisine != null ? new Cuisine(0, cuisine) : null);
		target.setDistance(distance);
		target.setCustomerRating(customerRating);
		target.setPrice(price);

		List<Restaurant> restaurants = finder.find(target);

		printResults(restaurants);

	}

	private static void printResults(List<Restaurant> restaurants) {
		switch (restaurants.size()) {
		case 0:
			System.out.println("Sorry, we didn't find any restaurant with this filter, please try again. ");
			break;
		case 1:
			System.out.println("We found just this restaurant with this filter: ");
			printRestaurants(restaurants);
			break;

		default:
			System.out.println("We found some restaurants with this filter, here are the best matches: ");
			printRestaurants(restaurants);
			break;
		}

		System.out.println("");
	}

	private static Integer readInteger(Scanner scanner) {
		String line = scanner.nextLine();
		if (!line.isBlank()) {
			return Integer.parseInt(line);
		}
		return null;
	}

	private static String readString(Scanner scanner) {
		String line = scanner.nextLine();
		if (line.isBlank()) {
			return null;
		}
		return line;
	}

	private static void printRestaurants(List<Restaurant> restaurants) {
		restaurants.forEach(restaurant -> System.out.println(restaurant));
	}

	private static void printCuisines(Map<String, Cuisine> cuisines) {
		cuisines.values().forEach(cuisine -> System.out.println(cuisine));
	}

}
