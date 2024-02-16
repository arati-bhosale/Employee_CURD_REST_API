package com.prowings.exception;

public class EmployeeNotPresentException extends Exception {

	String msg;

	public EmployeeNotPresentException(String msg) {
		super(msg);
		
	}
	
}
