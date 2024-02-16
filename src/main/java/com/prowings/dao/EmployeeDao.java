package com.prowings.dao;

import java.util.List;

import com.prowings.model.Employee;

public interface EmployeeDao {

	public void saveEmployee(Employee employee);

	public Employee getEmployeeById(int id);

	public List<Employee> getAllEmployee();
}
