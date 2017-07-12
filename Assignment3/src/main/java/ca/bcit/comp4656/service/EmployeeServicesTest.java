/**
 * Project: Assignment3
 * File: EmployeeServicesTest.java
 * Date: Jul 11, 2017
 * Time: 4:49:24 PM
 *//*

package ca.bcit.comp4656.service;

import static org.junit.Assert.*;

import org.junit.Test;

import ca.bcit.comp4656.jpa.entity.ClientResponse;
import ca.bcit.comp4656.jpa.entity.EmployeeObj;

*//**
 * @author Yashar Rahvar
 *
 *//*

public class EmployeeServicesTest {
	EmployeeServicesInterface employeeServices;
	
	*//**
	 * Testing find employee (firstName) 
	 *//*
	@Test
	public void testFindEmployeeFirstName() {
		ClientResponse clientResponse = employeeServices.find("A01234562");
		EmployeeObj employee =  clientResponse.getEmployee();
		assertTrue(employee.getFirstName().equalsIgnoreCase("Lisa"));
	}

	*//**
	 * Testing find employee (lastName) 
	 *//*
	@Test
	public void testFindEmployeeLastName() {
		ClientResponse clientResponse = employeeServices.find("A01234562");
		EmployeeObj employee =  clientResponse.getEmployee();
		assertTrue(employee.getLastName().equalsIgnoreCase("Simpson"));
	}
	
}
*/