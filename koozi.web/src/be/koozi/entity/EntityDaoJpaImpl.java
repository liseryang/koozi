package be.koozi.entity;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.orm.jpa.JpaCallback;
import org.springframework.orm.jpa.JpaTemplate;
import org.springframework.stereotype.Repository;


@Repository
@SuppressWarnings("unchecked")
public class EntityDaoJpaImpl<T, ID> implements EntityDao<T, ID> {

	private Class<T> clazz;

	private JpaTemplate jpaTemplate;

	@SuppressWarnings("unused")
	private EntityDaoJpaImpl() {}

	public EntityDaoJpaImpl(Class<T> clazz, JpaTemplate jpaTemplate) {
		this.jpaTemplate = jpaTemplate;
		this.clazz = clazz;
	}

	@Override
	public void create(T entity) {
		jpaTemplate.persist(entity);
	}

	@Override
	public void update(T entity) {
		entity = jpaTemplate.merge(entity);
	//	jpaTemplate.persist(entity);
	}

	@Override
	public T find(final Object entityId) {
		return jpaTemplate.find(clazz, entityId);
//		jpaTemplate.execute(new JpaCallback() {
//			public Object doInJpa(EntityManager em) throws PersistenceException {
//				return em.getReference(clazz, id);
//			}
//		}, true);
		
	}

	@Override
	public void delete(final Object entityId) {
		jpaTemplate.remove(jpaTemplate.find(clazz, entityId));
	}

	@Override
	public List<T> find(final Map<String, Object> params) {
		final StringBuffer queryStr = new StringBuffer();
		queryStr.append("select from ").append( clazz.getName()).append(" where ");
		boolean first = true;
		for (Iterator iterator = params.keySet().iterator(); iterator.hasNext();) {
			if(!first)
			{
				queryStr.append(" and ");
				
			}
			String type = (String) iterator.next();
			queryStr.append(type).append("= :").append(type);
			first = false;
		}
		
		final List<T> result = new LinkedList<T>();

		jpaTemplate.execute(new JpaCallback() {
				public Object doInJpa(EntityManager em) throws PersistenceException {
					Query query = em.createQuery(queryStr.toString());
					for (Iterator<Map.Entry<String, Object>> iterator = params.entrySet().iterator(); iterator.hasNext();) {
						Map.Entry<String, ?> entry = iterator.next();
						query.setParameter(entry.getKey(), entry.getValue());
					}
					
					List<T> entities = (List<T>) query.getResultList();
					for (T entity : entities) {
						result.add(entity);
					}
					return null;
				}
			}, true);
		return result;
	}
	
	@Override
	public List<T> findAll() {
		final List<T> result = new LinkedList<T>();

		jpaTemplate.execute(new JpaCallback() {
				public Object doInJpa(EntityManager em) throws PersistenceException {
					Query query = em.createQuery("Select from " + clazz.getName());
					List<T> entities = (List<T>) query.getResultList();
					for (T entity : entities) {
						result.add(entity);
					}
					return null;
				}
			}, true);
		return result;
	}

	protected JpaTemplate getJpaTemplate() {
		return this.jpaTemplate;
	}
}
