package com.sblox.business;

import com.sblox.common.dto.ActDto;
import com.sblox.common.exception.BaseException;

public interface ActivationBusiness {

	public void suspendNetlogicOrganization(ActDto actDto) throws BaseException;
	
	public ActDto getNetLogicOrganizationById(long id) throws BaseException;

}
