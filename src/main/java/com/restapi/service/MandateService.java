package com.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restapi.model.Mandate;
import com.restapi.repository.MandateRepository;

@Service
public class MandateService {

	@Autowired
	private MandateRepository mandateRepository; 
	public Mandate insertMandate(Mandate mandate) {
		 
		return mandateRepository.save(mandate);
	}

}
