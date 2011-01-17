package be.koozi.entity;

import java.util.List;
import java.util.Map;

public interface EntityDao<T, ID> {
	void create(T entity);

	void update(T entity);

	void delete(ID entityId);

	T find(ID entityId);

	List<T> findAll();

	List<T> find(Map<String, Object> params);
	
	List<T> find(Map<String, Object> params, String orderBy, Integer limit, Integer offset);
}
