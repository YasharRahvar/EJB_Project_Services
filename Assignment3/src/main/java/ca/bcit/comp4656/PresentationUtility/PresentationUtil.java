/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: PresentationUtil class
 * This class helps to get employees-presentation properties file. 
 */

package ca.bcit.comp4656.PresentationUtility;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

public class PresentationUtil {

	private static Logger log = Logger.getLogger(PresentationUtil.class);
	private static final String BUNDLE_NAME = "employees-presentation";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	/**
	 * 
	 * @param key
	 *            The key for properties file messages.
	 * @return This returns properties file message.
	 */
	public static String getString(String key) {
		try {
			if (System.getenv(key) != null) {
				return System.getenv(key);
			}
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			log.error("Unable to find key [" + key + "] in " + BUNDLE_NAME);
			return '!' + key + '!';
		}
	}
}
