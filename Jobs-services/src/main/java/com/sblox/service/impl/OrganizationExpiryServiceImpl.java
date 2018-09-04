package com.sblox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sblox.business.ActivationBusiness;
import com.sblox.business.OrganizationExpiryBusiness;
import com.sblox.common.dto.ActDto;
import com.sblox.common.dto.OrganizationsExpiryDto;
import com.sblox.common.exception.BaseException;
import com.sblox.common.util.Defines;
import com.sblox.service.ActivationService;
import com.sblox.service.OrganizationExpiryService;

@Service
public class OrganizationExpiryServiceImpl implements OrganizationExpiryService, Defines {

	@Autowired
	private OrganizationExpiryBusiness organizationExpiryBusiness;

	@Override
	public List<OrganizationsExpiryDto> getActiveOrganizations(boolean success) throws BaseException {
		// TODO Auto-generated method stub
		return organizationExpiryBusiness.getActiveOrganizations(success);
	}

	@Override
	public List<OrganizationsExpiryDto> getTrialOrganizations(boolean trial) throws BaseException {
		// TODO Auto-generated method stub
		return organizationExpiryBusiness.getTrialOrganizations(trial);
	}

}
