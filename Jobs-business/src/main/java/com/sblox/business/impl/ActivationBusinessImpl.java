package com.sblox.business.impl;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import com.netlogic.dataaccess.model.Act;
import com.netlogic.dataaccess.repository.ActRepository;
import com.sblox.business.ActivationBusiness;
import com.sblox.common.dto.ActDto;
import com.sblox.common.exception.BaseException;

public class ActivationBusinessImpl implements ActivationBusiness {

	@Autowired
	private Mapper mapper;

	@Autowired
	private ActRepository actRepository;

	@Override
	public void suspendNetlogicOrganization(ActDto actDto) throws BaseException {
		try {
			Act act = mapper.map(actDto, Act.class);
			actRepository.save(act);
		} catch (DataAccessException ex) {
			throw new BaseException();
		}
	}

	@Override
	public ActDto getNetLogicOrganizationById(long id) throws BaseException {
		Act act = actRepository.findById(id);
		if (act != null) {
			return mapper.map(act, ActDto.class, "ActDtoVsAct");
		} else {
			return null;
		}
	}

}
