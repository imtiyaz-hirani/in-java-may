package com.restapi.multithread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.restapi.multithread.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add/batch")
	public ResponseEntity<?> addEmployeeInBatch(@RequestParam("files") MultipartFile[] files) {
		try {
			for(MultipartFile file : files) {
				employeeService.insertEmployees(file.getInputStream());
			}
			
			return ResponseEntity.status(HttpStatus.OK).body("All employees saved..");
		}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Op failed..");
		}
	}
}
