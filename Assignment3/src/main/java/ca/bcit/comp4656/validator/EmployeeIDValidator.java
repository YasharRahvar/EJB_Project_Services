/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: EmployeeIDValidator class.
 * General class which helps us validate strings with regex .
 */
 
package ca.bcit.comp4656.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeIDValidator {
	/**
	 * 
	 * @param input
	 * @param pattern
	 * @return
	 */
	public static boolean isValidInput(String input, String pattern) {
		Pattern patt = Pattern.compile(pattern);
		Matcher match = patt.matcher(input);
		return match.matches();
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static String correctNameFormat(String name){
		String correctedName = name.substring(0, 1).toUpperCase() + name.substring(1);
		return correctedName;
	}
}
