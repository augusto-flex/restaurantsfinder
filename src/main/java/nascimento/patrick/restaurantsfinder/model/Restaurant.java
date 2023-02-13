package nascimento.patrick.restaurantsfinder.model;

import org.apache.commons.lang3.StringUtils;

public class Restaurant {

	// name,customer_rating,distance,price,cuisine_id

	private String name;
	private Integer customerRating; // 1-5 stars
	private Integer distance; // 1-10 miles
	private Integer price; // 10-50

	private Cuisine cuisine;
	
	public Restaurant() {
	}

	public Restaurant(String name, Integer customerRating, Integer distance, Integer price, Cuisine cuisine) {
		super();
		this.name = name;
		this.customerRating = customerRating;
		this.distance = distance;
		this.price = price;
		this.cuisine = cuisine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomerRating() {
		return customerRating;
	}

	public void setCustomerRating(Integer customerRating) {
		this.customerRating = customerRating;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Cuisine getCuisine() {
		return cuisine;
	}

	public void setCuisine(Cuisine cuisine) {
		this.cuisine = cuisine;
	}

	@Override
	public String toString() { 
		return StringUtils.rightPad(name, 20) + " [distance=" + distance + ", customerRating=" + customerRating + ", price="+ price + ", cuisine=" + cuisine.getName() + "]";
	}

}
