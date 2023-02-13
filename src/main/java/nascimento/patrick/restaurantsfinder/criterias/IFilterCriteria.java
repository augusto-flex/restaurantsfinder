package nascimento.patrick.restaurantsfinder.criterias;

import java.util.stream.Stream;

public interface IFilterCriteria<T> {
	
	public abstract Stream<T> filter(Stream<T> stream, T target);
	
	public abstract boolean accept(T target);
	
	

}
