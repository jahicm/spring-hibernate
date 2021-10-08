package org.employee.spring.hibernate.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

@Transactional
public abstract class GenericHibernateDao<T> implements IGenericDao<T> {

	@Autowired
	@Resource(name = "sessionFactory")
	protected SessionFactory sessionFactory;

	private Class<T> clazz;

	public void setClazz(final Class<T> clazzToSet) {

		this.clazz = clazzToSet;

	}

	public T findOne(final long id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	public List<T> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from " + clazz.getName()).list();

	}

	public void create(final T t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);

	}

	public T update(final T t) {

		return (T) sessionFactory.getCurrentSession().merge(t);
	}

	public void delete(T t) {

		sessionFactory.getCurrentSession().delete(t);
	}

}
