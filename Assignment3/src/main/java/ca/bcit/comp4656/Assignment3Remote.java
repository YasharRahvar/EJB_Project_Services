/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: Assignment3Remote interface.
 */
package ca.bcit.comp4656;

import javax.ejb.Remote;

import ca.bcit.comp4656.service.EmployeeServicesInterface;

@Remote
public interface Assignment3Remote extends EmployeeServicesInterface{

}
