package com.cognixia.jump.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognixia.jump.model.Employee;
import com.cognixia.jump.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repo;
	
	public List<Employee> getEmployees () {
		return repo.findAll();
	}
	
	public Employee getEmployeeById (int id) {
		Optional<Employee> found = repo.findById(id);
		
		if (found.isEmpty()) {
			return null;
		}
		
		return found.get();
		
		
	}
	
public Employee deleteEmployeeById(int id) {
		
		if (repo.existsById(id)) {
			
			Employee toDelete = getEmployeeById(id);
			
			repo.deleteById(id);
			
			return toDelete;
		}
		
		return null;
		
	}
	
	public Employee createEmployee (Employee employee) {
		
				
		employee.setId(-1);
		
		Employee added = repo.save(employee);
		
		return added;
		
	}
	
	
	public Employee updateEmployee (Employee employee) {
		
		
		if (repo.existsById(employee.getId())) {
			Employee updated = repo.save(employee);	
			return updated;
		}
				
		return null;
		
	}
	
	public List<Employee> findByLastName(String lastName) {
		return repo.findByLastName(lastName);
	}
	
	public List<Employee> findByFirstName(String firstName) {
		return repo.findByFirstName(firstName);
	}
	
	public boolean updateLastname(int id, String newLastname) {
				
		if( repo.existsById(id) ) {
			
			int count = repo.updateLastname(newLastname, id);
			
			if(count > 0) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean updateFirstname(int id, String newFirstname) {
		
		
		if( repo.existsById(id) ) {
			
			int count = repo.updateFirstname(newFirstname, id);
			
			if(count > 0) {
				return true;
			}
		}
		
		return false;
	}
	

}
