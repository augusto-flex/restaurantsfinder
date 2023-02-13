package nascimento.patrick.restaurantsfinder.csv;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import nascimento.patrick.restaurantsfinder.model.Cuisine;
import nascimento.patrick.restaurantsfinder.model.Restaurant;

public class CSVLoader {

	private static List<Restaurant> restaurants;
	private static Map<String, Cuisine> cuisines;

	public static List<Restaurant> getRestaurants() {
		if (restaurants == null) {
			restaurants = new ArrayList<>();
			loadRestaurants();
		}
		return restaurants;
	}

	public static Map<String, Cuisine> getCuisines() {
		if (cuisines == null) {
			cuisines = new HashMap<String, Cuisine>();
			loadCousines();
		}
		return cuisines;
	}

	private static List<Restaurant> loadRestaurants() {

		Map<String, Cuisine> cuisines = getCuisines();

		Map<Integer, Cuisine> cousinesById = cuisines.values().stream()
				.collect(Collectors.toMap(Cuisine::getId, Function.identity()));

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("csv/restaurants.csv");

		try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(is)).withSkipLines(1).build()) {
			List<String[]> restaurantsList = reader.readAll();

			for (String[] restaurantString : restaurantsList) {

				// name,customer_rating,distance,price,cuisine_id
				String name = restaurantString[0];
				Integer customerRating = Integer.parseInt(restaurantString[1]);
				Integer distance = Integer.parseInt(restaurantString[2]);
				Integer price = Integer.parseInt(restaurantString[3]);
				Integer cuisineId = Integer.parseInt(restaurantString[4]);
				Cuisine cuisine = cousinesById.get(cuisineId);

				Restaurant restaurant = new Restaurant(name, customerRating, distance, price, cuisine);
				restaurants.add(restaurant);

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}

		return restaurants;
	}

	private static Map<String, Cuisine> loadCousines() {

		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("csv/cuisines.csv");

		try (CSVReader reader = new CSVReaderBuilder(new InputStreamReader(is)).withSkipLines(1).build()) {
			List<String[]> cuisinesList = reader.readAll();

			cuisinesList.forEach(
					cuisine -> cuisines.put(cuisine[1], new Cuisine(Integer.parseInt(cuisine[0]), cuisine[1])));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CsvException e) {
			e.printStackTrace();
		}

		return cuisines;
	}

}
