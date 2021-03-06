package org.employee.spring.hibernate.dao;

import org.employee.hibernate.models.Dependent;
import org.springframework.stereotype.Repository;


@Repository("dependentDao")
public class DependentDaoImpl extends GenericHibernateDao<Dependent> {

	public DependentDaoImpl() {
		super();
		setClazz(Dependent.class);
	}

}
