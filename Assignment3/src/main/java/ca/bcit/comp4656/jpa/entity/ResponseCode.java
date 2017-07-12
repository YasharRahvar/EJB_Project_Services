/**
 * @author Yashar Rahvar
 * Date: 10/Jan/2017
 * Project: EJB Project 
 * Class: ResponseCode class.
 * This class helps us to generate messages with description and code to for the user. 
 */

package ca.bcit.comp4656.jpa.entity;

import java.io.Serializable;

public class ResponseCode implements Serializable {
	private static final long serialVersionUID = -9012043701443859679L;
	/**
	 * String code represent code for user messages.
	 */
	protected String code;
	/**
	 * String desc represent description for user messages.
	 */
	protected String desc;
	/**
	 * String error represent error for user messages.
	 */
	protected String error;

	/**
	 * 
	 * @return This returns code of responseCode.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param value
	 *            Sets code to responseCode code.
	 */
	public void setCode(String value) {
		this.code = value;
	}

	/**
	 * 
	 * @return This returns desc of responseCode.
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 
	 * @param value
	 *            Sets desc to responseCode desc.
	 */
	public void setDesc(String value) {
		this.desc = value;
	}

	/**
	 * 
	 * @return This returns error of responseCode.
	 */
	public String getError() {
		return error;
	}

	/**
	 * 
	 * @param error
	 *            Sets error to responseCode error.
	 */
	public void setError(String error) {
		this.error = error;
	}

	/**
	 * @return This returns toString of responseCode object.
	 */
	@Override
	public String toString() {
		return "ResponseCode [code=" + code + ", desc=" + desc + ", error=" + error + "]";
	}

}