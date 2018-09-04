package com.sblox.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.sblox.business.ActivationBusiness;
import com.sblox.business.MailBusiness;
import com.sblox.business.OrganizationExpiryBusiness;
import com.sblox.common.dto.ActDto;
import com.sblox.common.dto.MailDto;
import com.sblox.common.dto.OrganizationsExpiryDto;
import com.sblox.common.exception.BaseException;
import com.sblox.service.ActivationService;
import com.sblox.service.MailService;
import com.sblox.service.OrganizationExpiryService;
import com.sblox.common.util.Defines;

@Service
public class ActivationServiceImpl implements ActivationService, Defines {

	@Autowired
	private ActivationBusiness activationBusiness;

	@Override
	public void suspendNetlogicOrganization(ActDto actDto) throws BaseException {

		ActDto currentActivationResord = activationBusiness.getNetLogicOrganizationById(actDto.getId());
		currentActivationResord.setStatus(0);
		activationBusiness.suspendNetlogicOrganization(actDto);
	}

}
