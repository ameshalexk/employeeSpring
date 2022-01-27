package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognixia.jump.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	
	public List<Employee> findByLastName(String lastName);
	
	public List<Employee> findByFirstName(String firstName);
	
	@Transactional
	@Modifying 
	@Query("UPDATE Employee e SET e.lastName = :lastName WHERE e.id = :id")
	public int updateLastname(@Param(value="lastName") String lastname, @Param(value="id") int id);
	
	@Transactional
	@Modifying 
	@Query("UPDATE Employee e SET e.firstName = :firstName WHERE e.id = :id")
	public int updateFirstname(@Param(value="firstName") String firstname, @Param(value="id") int id);
	



}
