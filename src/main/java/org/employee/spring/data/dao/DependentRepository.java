package org.employee.spring.data.dao;

import org.employee.spring.data.models.Dependent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface DependentRepository extends JpaRepository<Dependent, Long> {

}
