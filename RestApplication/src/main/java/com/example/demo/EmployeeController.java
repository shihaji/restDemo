package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping("getEmployee/{id}")
	public ResponseEntity getEmployee(@PathVariable int id) {
		
		try {
		Employee emp=empRepo.findById(id).get();
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
		}catch(Exception e) {
			
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
	}
	
	
	@PostMapping("registerEmployee")
	public ResponseEntity<Object> registerEmployee(@RequestBody Employee employee) {
		
		try {
		empRepo.save(employee);
		return new ResponseEntity<Object>(HttpStatus.OK);
		}catch(Exception e) {
			
		return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("updateEmployee")
	public ResponseEntity updateEmployee(@RequestBody Employee employee) {
		
		try {
			
			Employee emp=empRepo.getById(employee.getId());
			
			emp.setName(employee.getName());
			emp.setSalary(employee.getSalary());
			
			empRepo.save(emp);
			
			return new ResponseEntity(employee,HttpStatus.OK);
			
		}catch(Exception e) {
			
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
	}
	
	@DeleteMapping("deleteEmployee/{id}")
	public ResponseEntity deleteEmployee(@PathVariable int id) {
		
		try {
			
			empRepo.deleteById(id);
			
			return new ResponseEntity(HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("getAllEmp")
	public ResponseEntity getAllEmp() {
		
		List<Employee> list=empRepo.findAll();
		
		return new ResponseEntity(list,HttpStatus.OK);
		
	}
	@GetMapping("getEmp/{id}/{name}")
	public ResponseEntity findByIdName(@PathVariable int id,@PathVariable String name) {
		
		try {
		Employee emp=empRepo.findByIdAndName(id, name);
		
		return new ResponseEntity(emp,HttpStatus.OK);
				
		}catch(Exception e) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
		
	}
	
	

}
