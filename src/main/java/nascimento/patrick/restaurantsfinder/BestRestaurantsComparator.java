package nascimento.patrick.restaurantsfinder;

import java.util.Comparator;

import nascimento.patrick.restaurantsfinder.model.Restaurant;

/*
 * Sort the restaurants by Distance first.
After the above process, if two matches are still equal, then the restaurant with a higher customer rating wins.
After the above process, if two matches are still equal, then the restaurant with a lower price wins.
After the above process, if two matches are still equal, then you can randomly decide the order.

Example: if the input is Customer Rating = 3 and Price = 15. 

Mcdonald’s is 	4 stars with an average spend = $10, and it is 1 mile away. 
KFC is 			3 stars with an average spend = $8,  and it is 1 mile away. 

Then we should consider Mcdonald’s as a better match than KFC. 
(They both matches the search criteria -> we compare distance -> we get a tie -> we then compare customer rating -> Mcdonald’s wins)
 */
public final class BestRestaurantsComparator implements Comparator<Restaurant> {

		@Override
		public int compare(Restaurant a, Restaurant b) {
			if(a.getDistance() == b.getDistance()) {
				if (a.getCustomerRating() == b.getCustomerRating()) {
					if(a.getPrice() == b.getPrice()) {
						
						// alphabtical order
						return a.getName().compareTo(b.getName());
					
					}
					return  a.getPrice() - b.getPrice(); //a-b, normal order
					
				}
				return b.getCustomerRating() - a.getCustomerRating(); // b-a, inverse
				
			}
			return a.getDistance() - b.getDistance(); //a-b, normal order
		}
		
	}