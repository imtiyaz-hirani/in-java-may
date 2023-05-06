package com.restapi.multithread.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.restapi.multithread.model.Employee;
import com.restapi.multithread.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Async
	public CompletableFuture<List<Employee>> insertEmployees(InputStream inputStream) throws Exception {
		List<Employee> list = new ArrayList<>();
		try(final BufferedReader brReader = new BufferedReader(new InputStreamReader(inputStream))) {
		String line = "";
		while ((line = brReader.readLine()) != null) {
			String[] data = line.split(",");
			/*
			 * data[0] : name of employee data[1] : salary of employee data[2] : city of
			 * employee
			 */
			Employee employee = new Employee(); // one object for one line
			employee.setName(data[0]);
			employee.setSalary(Double.parseDouble(data[1]));
			employee.setCity(data[2]);
			list.add(employee);
		}
		}
		list = employeeRepository.saveAll(list);
		return CompletableFuture.completedFuture(list);
	}
	/*
	 * @Async public CompletableFuture<List<Employee>> processRecords(InputStream
	 * inputStream) throws Exception{ BufferedReader brReader = new
	 * BufferedReader(new InputStreamReader(inputStream)); String line="";
	 * List<Employee> list = new ArrayList<>(); while((line = brReader.readLine())
	 * != null) { String[] data = line.split(",");
	 * 
	 * data[0] : name of employee data[1] : salary of employee data[2] : city of
	 * employee
	 * 
	 * Employee employee = new Employee(); //one object for one line
	 * employee.setName(data[0]); employee.setSalary(Double.parseDouble(data[1]));
	 * employee.setCity(data[2]); list.add(employee); } //List has all the records
	 * is available now
	 * 
	 * Save the list in DB list = employeeRepository.saveAll(list); return
	 * CompletableFuture.completedFuture(list); }
	 */

}
