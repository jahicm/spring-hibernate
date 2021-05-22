package org.employee.spring.data.dao;

import java.util.List;
import java.util.Optional;

import org.employee.spring.data.models.Project;
import org.springframework.beans.factory.annotation.Autowired;

public class ProjectServiceImpl implements GenericService<Project> {

	@Autowired
	private ProjectRepository projectRepository;

	public ProjectServiceImpl() {
		super();

	}

	public void create(Project project) {
		projectRepository.saveAndFlush(project);

	}

	public Project update(Project project) {
		Optional<Project> pro = projectRepository.findById(project.getProjectNumber());
		pro = Optional.ofNullable(project);

		if (!pro.isEmpty())
			return projectRepository.saveAndFlush(pro.get());

		return project;
	}

	public List<Project> findAll() {

		return projectRepository.findAll();
	}

	public void delete(Project department) {
		projectRepository.delete(department);

	}

}