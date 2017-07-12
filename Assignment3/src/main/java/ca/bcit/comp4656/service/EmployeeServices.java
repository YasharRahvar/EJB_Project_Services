/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: EmployeeServices class.
 * This class is responsible for validate user inputs and setting user response messages.
 * This class also is responsible for calling EmployeeFacade methods for database operations. 
 */
package ca.bcit.comp4656.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ca.bcit.comp4656.Assignment3Local;
import ca.bcit.comp4656.Assignment3Remote;
import ca.bcit.comp4656.PresentationUtility.PresentationUtil;
import ca.bcit.comp4656.jpa.data.EmployeeFacadeInterface;
import ca.bcit.comp4656.jpa.entity.ClientResponse;
import ca.bcit.comp4656.jpa.entity.EmployeeObj;
import ca.bcit.comp4656.jpa.entity.ResponseCode;
import ca.bcit.comp4656.validator.EmployeeIDValidator;

@Stateless(mappedName = "employeeServicesEJB")
public class EmployeeServices implements Assignment3Local, Assignment3Remote {
	//	Employee ID correct format regex.
	private final static String ID_PATTERN = "[A][0][0-9]{7}";
	
	private static Logger log = Logger.getLogger(EmployeeServices.class);

	/**
	 * Instance of employeeFacadeInterface
	 */
	@EJB
	private EmployeeFacadeInterface employeeFacadeInterface;

	/**
	 * Find method is responsible to get employee ID from the client and return employee if it exist or appropriate 
	 * response in case employee did not exist.
	 * @param ID The user input for employee ID.
	 * @return This returns ClientReponse object.
	 */
	@Override
	public ClientResponse find(String ID) {
		ResponseCode responseCode = new ResponseCode();
		ClientResponse clientResponse = new ClientResponse();
		EmployeeObj employee = new EmployeeObj();

		
		if (!EmployeeIDValidator.isValidInput(ID, ID_PATTERN)) {
			responseCode.setError(PresentationUtil.getString("error.find.employee.invalid.desc"));
			log.error("Invalid ID Format.");
		} else {
			employee = employeeFacadeInterface.findEmployeeById(ID);
			log.info("Emplyoee format is correct.");
			if (employee == null) {
				log.error("Emplyoee doesn't exist.");
				responseCode.setDesc(PresentationUtil.getString("error.find.employee.desc"));
				responseCode.setCode(PresentationUtil.getString("error.find.employee.code"));
			} else {
				log.info("Emplyoee exist.");
				responseCode.setDesc(PresentationUtil.getString("find.employee.success.desc"));
				responseCode.setCode(PresentationUtil.getString("find.employee.success.code"));
			}
		}
		clientResponse.setEmployee(employee);
		clientResponse.setResponseCode(responseCode);
		return clientResponse;
	}

	/**
	 * Delete method is responsible to get employee ID from the client and delete employee if it exist or appropriate 
	 * response in case employee did not exist.
	 * @param ID The user input for employee ID.
	 * @return This returns ResponseCode object.
	 */
	@Override
	public ResponseCode delete(String ID) {
		ResponseCode responseCode = new ResponseCode();

		if (!EmployeeIDValidator.isValidInput(ID, ID_PATTERN)) {
			log.error("Invalid ID Format.");
			responseCode.setError(PresentationUtil.getString("error.find.employee.invalid.desc"));
		} else {
			log.info("Emplyoee format is correct.");
			if (employeeFacadeInterface.deleteEmployeeById(ID)) {
				log.debug("Emplyoee exist.");
				responseCode.setDesc(PresentationUtil.getString("remove.employee.success.desc"));
				responseCode.setCode(PresentationUtil.getString("remove.employee.success.code"));
			} else {
				log.error("Emplyoee doesn't exist.");
				responseCode.setDesc(PresentationUtil.getString("error.remove.employee.desc"));
				responseCode.setCode(PresentationUtil.getString("error.remove.employee.code"));
			}
		}
		return responseCode;
	}

	/**
	 * Add method is responsible to get employee ID,firstName,lastName,dob from the client and add employee if it passes
	 * all the validations or sends appropriate response in case it does not pass the validation.  
	 * @param ID The user input for employee ID.
	 * @param firstName The user input for employee firstName.
	 * @param lastName The user input for employee lastName.
	 * @param dob The user input for employee dob.
	 * @return This returns a responseCode.
	 */
	@Override
	public ResponseCode add(String ID, String firstName, String lastName, String dob) {
		ResponseCode responseCode = new ResponseCode();
		EmployeeObj employee = null;

		if (EmployeeIDValidator.isValidInput(ID, ID_PATTERN)) {
			log.info("Valid ID Format.");
			employee = employeeFacadeInterface.findEmployeeById(ID);
		}
		if (employee == null ) {
			try {
				log.info("No duplication founds.");
				employeeFacadeInterface.addEmployee(ID, firstName, lastName, dob);
				responseCode.setDesc(PresentationUtil.getString("add.employee.success.desc"));
				responseCode.setCode(PresentationUtil.getString("add.employee.success.code"));
				log.debug("Employee added successfully.");
			} 
			catch (Exception e1) {
				log.error("Error adding employee.");
				responseCode.setError(e1.getMessage());
			}
		} else {
			log.error("Duplicate employee ID. No employee added.");
			responseCode.setDesc(PresentationUtil.getString("error.add.employee.duplicate.desc"));
			responseCode.setCode(PresentationUtil.getString("error.add.employee.invalid.code"));
		}
		return responseCode;
	}

	/**
	 * @return This returns a list of employees.
	 */
	@Override
	public List<EmployeeObj> getEmployee() {
		return employeeFacadeInterface.getPeople();
	}

	public boolean validation(EmployeeObj employee) {
		boolean check = false;
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<EmployeeObj>> constraintViolations = validator.validate(employee);

		if (constraintViolations.size() > 0) {
			check = false;
			Set<String> violationMessages = new HashSet<String>();

			for (ConstraintViolation<EmployeeObj> constraintViolation : constraintViolations) {
				violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
			}

			throw new RuntimeException(StringUtils.join(violationMessages, "\n"));
		} else {
			check = true;
		}
		return check;
	}

}
