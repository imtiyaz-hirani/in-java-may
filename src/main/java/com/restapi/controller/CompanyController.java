package com.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restapi.model.Company;
import com.restapi.service.CompanyService;

@RestController 
@RequestMapping("/company")
public class CompanyController {

	@Autowired
	private CompanyService companyService;
	//GET, POST,PUT,DELETE 
	/* 
	 	PATH: /company/add
	 	REQUEST: Company Object in JSON format 
	 	{
	 		"name": "BLUEDART EXP",
	 		"city": "Mumbai",
	 		"level": 1,
	 		"parentId":null
	 	}
	 	-- Jackson will convert this JSON into a Java Object
	 */
	@PostMapping("/add")
	public Company insertCompany(@RequestBody Company company) {
		//reach out to service and give company and ask it to save it in DB and return company
		return companyService.insertCompany(company);
	}
	
	
}
