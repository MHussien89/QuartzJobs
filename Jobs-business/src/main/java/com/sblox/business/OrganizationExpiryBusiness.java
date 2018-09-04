package com.sblox.business;

import java.util.List;

import com.sblox.common.dto.OrganizationsExpiryDto;
import com.sblox.common.exception.BaseException;

public interface OrganizationExpiryBusiness {

	public List<OrganizationsExpiryDto> getActiveOrganizations(boolean success) throws BaseException;

	public List<OrganizationsExpiryDto> getTrialOrganizations(boolean trial) throws BaseException;

}
