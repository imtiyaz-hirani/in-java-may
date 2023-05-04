package com.restapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.model.Company;
import com.restapi.repository.CompanyRepository;

@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	public Company insertCompany(Company company) {
		return companyRepository.save(company);
	}

	public Company getCompanyByName(String name) {
		return companyRepository.findByName(name);
	}

	public Company fetchCompany(Long cid) {
		Optional<Company> optional = companyRepository.findById(cid);
		if(!optional.isPresent()) {
			return null; //go with exception 
		}
		return optional.get();
	}

	
}
