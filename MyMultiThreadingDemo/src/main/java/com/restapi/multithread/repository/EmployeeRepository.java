package com.restapi.multithread.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.multithread.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
