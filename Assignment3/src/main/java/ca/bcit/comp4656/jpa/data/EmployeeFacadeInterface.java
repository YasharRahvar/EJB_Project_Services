/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: Employee Facade Interface
 * 
 */
package ca.bcit.comp4656.jpa.data;

import java.util.List;

import ca.bcit.comp4656.jpa.entity.EmployeeObj;

public interface EmployeeFacadeInterface {

	/**
	 * Getting list of employees method.
	 * 
	 * @return This returns the list of employees.
	 */
	public List<EmployeeObj> getPeople();

	/**
	 * Finding an employee method
	 * 
	 * @param employeeID
	 *            The employee ID of the employee.
	 * @return This returns an employee.
	 */
	public EmployeeObj findEmployeeById(String employeeID);

	/**
	 * Deleting an employee method
	 * 
	 * @param employeeID
	 *            The employee ID of the employee.
	 * @return this returns a boolean true: if deletion was successful and false if unsuccessful
	 */
	public boolean deleteEmployeeById(String employeeID);

	/**
	 * Adding an employee method
	 * 
	 * @param ID
	 *            The employee ID of the employee.
	 * @param firstName
	 *            The first name of the employee.
	 * @param lastName
	 *            the last name of the employee.
	 * @param dob
	 *            the date of birth of the employee.
	 */
	public void addEmployee(String ID, String firstName, String lastName, String dob);

}
