package com.restapi.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.restapi.enums.MandateType;

@Entity
public class Mandate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id; 
	
	@Enumerated(EnumType.STRING)
	private MandateType mandateType;
	
	private String country;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MandateType getMandateType() {
		return mandateType;
	}

	public void setMandateType(MandateType mandateType) {
		this.mandateType = mandateType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	} 
	
	
}
