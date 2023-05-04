package com.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.restapi.model.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>{

	
	/* All the methods of JpaRepository are now available in CompanyRepo */
	Company findByName(String name);
}
