package org.employee.spring.data.dao;

import java.util.List;
import java.util.Optional;

import org.employee.spring.data.models.Department;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentServiceImpl implements GenericService<Department> {

	@Autowired
	private DepartmentRepository departmentRepository;

	public DepartmentServiceImpl() {
		super();

	}

	public void create(Department department) {
		departmentRepository.saveAndFlush(department);

	}

	public Department update(Department department) {
		Optional<Department> dep = departmentRepository.findById(department.getDeptNo());
		dep = Optional.ofNullable(department);

		if (!dep.isEmpty())
			return departmentRepository.saveAndFlush(dep.get());

		return department;
	}

	public List<Department> findAll() {

		return departmentRepository.findAll();
	}

	public void delete(Department department) {
		departmentRepository.delete(department);

	}

}