/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: Employee entity class.
 * This class is a employee entity representing employee table in the database. 
 */
package ca.bcit.comp4656.jpa.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({ @NamedQuery(name = "EmployeeObj.findById", query = "select p from EmployeeObj p where p.id= :id"),
		@NamedQuery(name = "EmployeeObj.deleteById", query = "delete from EmployeeObj where id= :id"),
		@NamedQuery(name = "EmployeeObj.getAllEmployee", query = "select e from EmployeeObj e") })
@Table(name = "employee")
public class EmployeeObj implements Serializable {

	private static final long serialVersionUID = 4106360764146241353L;

	/**
	 * String id representing employee ID in the employee table (Primary Key).
	 * Correct format: A0xxxxxxx
	 */
	@Id
	@Pattern(regexp = "[A][0][0-9]{7}")
	@NotNull(message = "Should not be empty.")
	@Column(unique = true)
	private String id;

	/**
	 * String firstName representing employee firstName in the employee table.
	 * Correct Format: Not Null
	 */
	@Column
	@NotNull
	@Size(min = 1, message = "Should not be empty.")
	private String firstName;

	/**
	 * String lastName representing employee lastName in the employee table.
	 * Correct Format: Not Null
	 */
	@Column
	@NotNull
	@Size(min = 1, message = "Should not be empty.")
	private String lastName;

	/**
	 * Date dob representing employee dob in the employee table.
	 * Correct Format: yyyy/MM/dd
	 */
	@Column
	@NotNull(message = "Invalid dob format.")
	private Date dob;

	/**
	 * 
	 * @return This returns Employee id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 *            Sets id to employee id.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 
	 * @return This returns Employee firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * 
	 * @param firstName
	 *            Sets firstName to employee firtName.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return This returns employee lastName.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * 
	 * @param lastName
	 *            Sets lastName to employee lastName.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * 
	 * @return This returns employee dob.
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * 
	 * @param dob
	 *            Sets dob to employee dob.
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * @return This returns employee toString.
	 */
	@Override
	public String toString() {
		return "EmployeeObj [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + "]";
	}

}
