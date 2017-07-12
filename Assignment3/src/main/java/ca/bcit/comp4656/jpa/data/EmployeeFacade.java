/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: Employee Facade
 * This class is responsible for doing database operations. 
 */
package ca.bcit.comp4656.jpa.data;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import ca.bcit.comp4656.jpa.entity.EmployeeObj;

@Stateless(mappedName = "EmployeeFacadeEJB")
@Local
public class EmployeeFacade implements EmployeeFacadeInterface {

	private static Logger log = Logger.getLogger(EmployeeFacade.class);
	/**
	 * Instance of EntityManager
	 */
	@PersistenceContext(unitName = "PersonPU")
	EntityManager entityManager;

	/**
	 * Getting list of employees method.
	 * 
	 * @return This returns the list of employees.
	 */
	public List<EmployeeObj> getPeople() {
		return entityManager.createNamedQuery("EmployeeObj.getAllEmployee", EmployeeObj.class).getResultList();
	}

	/**
	 * Finding an employee method.
	 * 
	 * @param employeeID
	 *            The employee ID of the employee.
	 * @return This returns an employee.
	 */
	public EmployeeObj findEmployeeById(String employeeID) {
		try {
			log.info("Employee found.");
			return entityManager.createNamedQuery("EmployeeObj.findById", EmployeeObj.class)
					.setParameter("id", employeeID).getSingleResult();
		} catch (NoResultException nre) {
			log.info("No result found.");
			return null;
		}
	}

	/**
	 * Deleting an employee method
	 * 
	 * @param employeeID
	 *            The employee ID of the employee.
	 * @return this returns a boolean true: if deletion was successful and false if unsuccessful
	 */
	public boolean deleteEmployeeById(String employeeID) {
		boolean checkForDelete = false;
		EmployeeObj employee = findEmployeeById(employeeID);
		if (employee == null) {
			log.info("No employee found.");
			checkForDelete = false;
		} else {
			entityManager.remove(findEmployeeById(employeeID));
			log.info("Employee found and removed.");
			checkForDelete = true;
		}
		return checkForDelete;
	}

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
	public void addEmployee(String ID, String firstName, String lastName, String dob) {
		EmployeeObj employee = new EmployeeObj();
		employee.setId(ID.trim());
		employee.setFirstName(firstName.trim());
		employee.setLastName(lastName.trim());
		DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		try {
			employee.setDob(format.parse(dob));
		} catch (ParseException e) {
			log.error("Error while parsing date format.");
			e.printStackTrace();
		}
		if (validation(employee)) {
			entityManager.persist(employee);
		}
		entityManager.persist(employee);
	}

	/**
	 * Closing an EntityManger method.
	 */
	public void closeEntityManager() {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
	}

	/**
	 * This method checks if there are any validation error while users tries to add a new employee.
	 * 
	 * @param employee
	 * @return returns a boolean if false validation set has values, if true validation set has no values.
	 * 
	 */
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
