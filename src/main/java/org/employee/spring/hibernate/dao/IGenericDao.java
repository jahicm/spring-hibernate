package org.employee.spring.hibernate.dao;

import java.util.List;



public interface IGenericDao<T> {

	public void setClazz(final Class<T> clazzToSet);
	
	public void delete(T t);

	public T findOne(final long id);

	public List<T> findAll();

	public void create(final T t);

	public T update(final T t);
}
