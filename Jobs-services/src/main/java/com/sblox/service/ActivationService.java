package com.sblox.service;

import java.util.List;

import com.sblox.common.dto.ActDto;
import com.sblox.common.exception.BaseException;

public interface ActivationService {

	public void suspendNetlogicOrganization(ActDto actDto) throws BaseException;
}
