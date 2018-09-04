package com.sblox.business.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.sblox.dataaccess.model.Mail;
import com.sblox.dataaccess.model.OrganizationsExpiry;
import com.sblox.dataaccess.repository.MailRepository;
import com.sblox.dataaccess.repository.OrganizationsExpiryRepository;
import com.sblox.business.MailBusiness;
import com.sblox.business.OrganizationExpiryBusiness;
import com.sblox.common.dto.MailDto;
import com.sblox.common.dto.OrganizationsExpiryDto;
import com.sblox.common.exception.BaseException;
//import com.sblox.dataaccess.model.Mail;
//import com.mail.sblox.dataaccess.repository;

@Service("organizationExpiryBusiness")
public class OrganizationExpiryBusinessImpl implements OrganizationExpiryBusiness {

	@Autowired
	private Mapper mapper;

	@Autowired
	private OrganizationsExpiryRepository organizationsExpiryRepository;

	@Override
	public List<OrganizationsExpiryDto> getActiveOrganizations(boolean success) throws BaseException {
		try {
			ArrayList<OrganizationsExpiry> mails = (ArrayList<OrganizationsExpiry>) organizationsExpiryRepository
					.findByActive(success);

			ArrayList<OrganizationsExpiryDto> organizationsExpiryDtos = new ArrayList<OrganizationsExpiryDto>();
			for (int i = 0; i < mails.size(); i++) {
				OrganizationsExpiryDto organizationsExpiryDto = mapper.map(mails.get(i), OrganizationsExpiryDto.class);
				organizationsExpiryDtos.add(organizationsExpiryDto);
			}
			return organizationsExpiryDtos;
		} catch (DataAccessException ex) {
			throw new BaseException();
		}

	}

	@Override
	public List<OrganizationsExpiryDto> getTrialOrganizations(boolean trial) throws BaseException {
		try {
			ArrayList<OrganizationsExpiry> mails = (ArrayList<OrganizationsExpiry>) organizationsExpiryRepository
					.findByTrial(trial);

			ArrayList<OrganizationsExpiryDto> organizationsExpiryDtos = new ArrayList<OrganizationsExpiryDto>();
			for (int i = 0; i < mails.size(); i++) {
				OrganizationsExpiryDto organizationsExpiryDto = mapper.map(mails.get(i), OrganizationsExpiryDto.class);
				organizationsExpiryDtos.add(organizationsExpiryDto);
			}
			return organizationsExpiryDtos;
		} catch (DataAccessException ex) {
			throw new BaseException();
		}

	}

}
