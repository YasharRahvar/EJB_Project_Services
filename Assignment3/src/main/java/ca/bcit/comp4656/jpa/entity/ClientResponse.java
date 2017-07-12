/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: ClientResponse class
 * This class has employee and responseCode fields and it helps us to send messages to the client. 
 */

package ca.bcit.comp4656.jpa.entity;

import java.io.Serializable;

public class ClientResponse implements Serializable {

	private static final long serialVersionUID = -6934925174425172524L;
	/**
	 * Employee Object.
	 */
	private EmployeeObj employee = null;
	/**
	 * ResponseCode object.
	 */
	private ResponseCode responseCode = null;

	/**
	 * 
	 * @return This returns an employee object.
	 */
	public EmployeeObj getEmployee() {
		return employee;
	}

	/**
	 * 
	 * @param employee
	 *            sets employee object
	 */
	public void setEmployee(EmployeeObj employee) {
		this.employee = employee;
	}

	/**
	 * 
	 * @return This returns responseCode object.
	 */
	public ResponseCode getResponseCode() {
		return responseCode;
	}

	/**
	 * 
	 * @param responseCode
	 *            sets reposneCode object.
	 */
	public void setResponseCode(ResponseCode responseCode) {
		this.responseCode = responseCode;
	}

}
