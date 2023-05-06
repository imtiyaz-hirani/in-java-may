package com.restapi.enums;

public enum LoanType {
	PERSONAL(15),HOME(7.5),VEHICLE(11);
	LoanType(double interestRate){
		this.interestRate = interestRate;
	}	
	private double interestRate;
	public double getInterestRate() {
		return interestRate;
	}
}
