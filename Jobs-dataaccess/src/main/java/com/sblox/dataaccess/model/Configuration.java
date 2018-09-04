package com.sblox.dataaccess.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mostafa Hussien
 */
@Entity(name = "M_Configuration")
public class Configuration extends IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -121307434351701583L;

	@Id
	private String name;

	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
