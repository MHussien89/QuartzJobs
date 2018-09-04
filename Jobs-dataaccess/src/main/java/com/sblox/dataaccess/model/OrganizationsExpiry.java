package com.sblox.dataaccess.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Mostafa Hussien
 */
@Entity(name = "M_Organization_Expiry")
public class OrganizationsExpiry extends IEntity {

	@Id
	private int organizationId;
	private String username;
	private String email;
	private String endDate;
	private boolean active;
//	private boolean trial;

	public int getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(int organizationId) {
		this.organizationId = organizationId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

//	public boolean isTrial() {
//		return trial;
//	}
//
//	public void setTrial(boolean trial) {
//		this.trial = trial;
//	}

}
