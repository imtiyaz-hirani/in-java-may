package com.restapi.enums;

public enum MandateType {
LOCAL(0,100000),THIRD_PARTY(5,50000),INTL(20,200000),OTHER(10,20000); 
	
	MandateType(int charge,int limit){
		this.charge = charge;
		this.limit = limit; 
	}
	
	private int charge;
	private int limit; 

	public int getCharge() {
		return charge;
	}

	public int getLimit() {
		return limit;
	} 
	
	

}
//MandateType.LOCAL.getCharge()
