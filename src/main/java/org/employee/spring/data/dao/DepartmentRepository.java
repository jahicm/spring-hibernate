package org.employee.spring.data.dao;

import org.employee.spring.data.models.Department;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
