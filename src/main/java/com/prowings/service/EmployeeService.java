package com.prowings.service;

import java.util.List;

import com.prowings.model.Employee;

public interface EmployeeService {

	public void saveEmployee(Employee employee);

	public Employee getEmployeeById(int id);

	public List<Employee> getAllEmployee();

}
