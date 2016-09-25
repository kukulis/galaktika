package lt.gt.galaktika.model.dao;

import java.util.List;

import org.hibernate.jdbc.Work;

public interface IDAO {
	<E> E create(E e);

	<E> E update(E e);

	<E> void delete(Class<E> clazz, long id);

	<E> E find(Class<E> clazz, long id);

	<E> List<E> find(Class<E> clazz, String query, int min, int max);

	<E> List<E> namedFind(Class<E> clazz, String query, int min, int max);
	
	void work (Work w);
}
