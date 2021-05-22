package org.employee.spring.data.dao;

import java.util.List;

public interface GenericService<T> {

	public List<T> findAll();

	public void create(final T t);

	public T update(final T t);

	public void delete(T t);

}
