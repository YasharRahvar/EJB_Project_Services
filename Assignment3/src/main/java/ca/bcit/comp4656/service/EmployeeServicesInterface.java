/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: EmployeeServicesInterface interface. 
 */
package ca.bcit.comp4656.service;

import java.util.List;

import ca.bcit.comp4656.jpa.entity.ClientResponse;
import ca.bcit.comp4656.jpa.entity.EmployeeObj;
import ca.bcit.comp4656.jpa.entity.ResponseCode;

public interface EmployeeServicesInterface {

	/**
	 * 
	 * @param ID The Employee ID of the employee.
	 * @return This returns a ClientResponse.
	 */
	public ClientResponse find(String ID);
	
	/**
	 * 
	 * @param ID The Employee ID of the employee.
	 * @return This returns a ResponseCode.
	 */
	public ResponseCode delete(String ID);
	
	/**
	 * 
	 * @param ID The Employee ID of the employee.
	 * @param firstName The Employee firstName of the employee.
	 * @param lastName The Employee lastName of the employee.
	 * @param dob The Employee dob of the employee.
	 * @return This returns a ResponseCode.
	 */
	public ResponseCode add(String ID, String firstName, String lastName, String dob);
	
	/**
	 * 
	 * @return This returns list of all employees.
	 */
	public List<EmployeeObj> getEmployee();
}
