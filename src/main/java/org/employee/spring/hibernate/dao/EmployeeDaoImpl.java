package org.employee.spring.hibernate.dao;

import org.employee.hibernate.models.Employee;
import org.springframework.stereotype.Repository;


@Repository("employeeDao")
public class EmployeeDaoImpl extends GenericHibernateDao<Employee> {

	
	public EmployeeDaoImpl() {
		super();
		setClazz(Employee.class);
	}

}