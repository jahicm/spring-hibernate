package org.employee.spring.hibernate.dao;

import org.employee.hibernate.models.Project;
import org.springframework.stereotype.Repository;

@Repository("projectDao")
public class ProjectDaoImpl extends GenericHibernateDao<Project> {

	public ProjectDaoImpl() {
		super();
		setClazz(Project.class);
	}
}
