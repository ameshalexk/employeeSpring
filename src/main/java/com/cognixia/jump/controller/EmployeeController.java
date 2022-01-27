package com.cognixia.jump.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Employee;
import com.cognixia.jump.service.EmployeeService;


@RequestMapping("/api")
@RestController
public class EmployeeController {

	@Autowired
	EmployeeService service;
	
	@GetMapping("/employee")
	public List<Employee> getEmployees() {
		return service.getEmployees();
	} 
	
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable int id) {
		
		Employee employee = service.getEmployeeById(id);
		
		if(employee == null ) {
			return ResponseEntity.status(400).body("Could not find employee");
		}
		return ResponseEntity.status(200).body(employee);
		
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable int id) {
		Employee employee = service.deleteEmployeeById(id);

		if (employee == null) {
			return ResponseEntity.status(404).body("Could not find employee");
		}
		return ResponseEntity.status(200).body(employee);

	}
	
	@PostMapping("/employee")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {

		Employee created = service.createEmployee(employee);

		return ResponseEntity.status(201).header("employee id", created.getId() + "").body(created);

	}

	@PutMapping("/employee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee) {
		Employee updated = service.updateEmployee(employee);

		if (updated == null) {
			return ResponseEntity.status(404).body("Could not fnd employee with id : " + employee.getId());
		}
		return ResponseEntity.status(200).body(updated);

	}

	@PatchMapping("/employee/update/lastname")
	public ResponseEntity<?> updateLastname(@PathParam(value="id") int id, @PathParam(value="lastname") String lastname) {
		boolean updated = service.updateLastname(id, lastname);
		System.out.println(lastname + "check" + id);

		if (updated) {
			return ResponseEntity.status(200).body("Last name updated");
		}
		return ResponseEntity.status(400).body("Could not update Last name");

	}
	
	@PatchMapping("/employee/update/firstname")
	public ResponseEntity<?> updateFirstname(@PathParam(value="id") int id, @PathParam(value="firstname") String firstname) {
		boolean updated = service.updateFirstname(id, firstname);

		if (updated) {
			return ResponseEntity.status(200).body("First name updated");
		}
		return ResponseEntity.status(400).body("Could not update First name");

	}
	
	
	@GetMapping("/employee/lastName/{lastName}")
	public List<Employee> findByLastName(@PathVariable String lastName) {
		
		return service.findByLastName(lastName);
				
	}
	
	@GetMapping("/employee/firstName/{firstName}")
	public List<Employee> findByFirstName(@PathVariable String firstName) {
		
		return service.findByFirstName(firstName);
				
	}
	
	

}
