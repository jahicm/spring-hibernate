package org.employee.spring.data.dao;

import java.util.List;
import java.util.Optional;

import org.employee.spring.data.models.Dependent;
import org.springframework.beans.factory.annotation.Autowired;


public class DependentServiceImpl implements GenericService<Dependent> {

	@Autowired
	private DependentRepository dependentRepository;

	public DependentServiceImpl() {
		super();

	}

	public void create(Dependent dependent) {
		dependentRepository.saveAndFlush(dependent);

	}

	public Dependent update(Dependent dependent) {
		Optional<Dependent> dep = dependentRepository.findById(dependent.getDep_ssn());
		dep = Optional.ofNullable(dependent);

		if (!dep.isEmpty())
			return dependentRepository.saveAndFlush(dependent);

		return dependent;
	}

	public List<Dependent> findAll() {

		return dependentRepository.findAll();
	}

	public void delete(Dependent dependent) {
		dependentRepository.delete(dependent);

	}

}