/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: Assignment3Local interface.
 */

package ca.bcit.comp4656;

import javax.ejb.Local;

import ca.bcit.comp4656.service.EmployeeServicesInterface;

@Local
public interface Assignment3Local extends EmployeeServicesInterface{

}
