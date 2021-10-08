package org.employee.spring.hibernate.dao;

import org.employee.hibernate.models.Department;
import org.springframework.stereotype.Repository;


@Repository("departmentDao")
public class DepartmentDaoImpl extends GenericHibernateDao<Department> {

	public DepartmentDaoImpl() {
		super();
		setClazz(Department.class);
	}
}
