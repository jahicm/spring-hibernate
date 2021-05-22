package org.employee.spring.data.dao;

import java.util.List;
import java.util.Optional;

import org.employee.spring.data.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeServiceImpl implements GenericService<Employee> {

	@Autowired
	private EmployeeRepository employeeRepository;

	public EmployeeServiceImpl() {
		super();

	}

	public void create(Employee employee) {
		employeeRepository.saveAndFlush(employee);

	}

	public Employee update(Employee employee) {
		Optional<Employee> emp = employeeRepository.findById(employee.getSsn());
		emp = Optional.ofNullable(employee);

		if (!emp.isEmpty())
			return employeeRepository.saveAndFlush(emp.get());

		return employee;
	}

	public List<Employee> findAll() {

		return employeeRepository.findAll();
	}

	public void delete(Employee employee) {
		employeeRepository.delete(employee);

	}

}