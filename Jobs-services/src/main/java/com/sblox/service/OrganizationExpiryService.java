package com.sblox.service;

import java.util.List;

import com.sblox.common.dto.MailDto;
import com.sblox.common.dto.OrganizationsExpiryDto;
import com.sblox.common.exception.BaseException;

public interface OrganizationExpiryService {

	public List<OrganizationsExpiryDto> getActiveOrganizations(boolean success) throws BaseException;

	public List<OrganizationsExpiryDto> getTrialOrganizations(boolean trial) throws BaseException;

}
