package com.sblox.jobs;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerContext;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;

import com.sblox.business.MailBusiness;
import com.sblox.business.OrganizationExpiryBusiness;
import com.sblox.common.dto.ActDto;
import com.sblox.common.dto.MailDto;
import com.sblox.common.dto.OrganizationsExpiryDto;
import com.sblox.common.exception.BaseException;
import com.sblox.service.ActivationService;
import com.sblox.service.MailService;
import com.sblox.service.OrganizationExpiryService;

public class OrganizationTrialJob implements Job {

	private Logger logger = Logger.getLogger(OrganizationTrialJob.class);

	private MailService emailServiceImpl;

	@Autowired
	private OrganizationExpiryService organizationExpiryServiceImpl;

	@Autowired
	private ActivationService activationServiceImpl;

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		logger.debug("DriverNotificationJob.execute");
		SchedulerContext context;
		try {
			context = jec.getScheduler().getContext();

			// mailBusiness = (MailBusiness) context.get("mailBusiness");
			organizationExpiryServiceImpl = (OrganizationExpiryService) context.get("organizationExpiryServiceImpl");
			activationServiceImpl = (ActivationService) context.get("activationServiceImpl");
//			emailServiceImpl = (MailService) context.get("emailServiceImpl");

			List<OrganizationsExpiryDto> organizationsExpiryDtos = organizationExpiryServiceImpl
					.getTrialOrganizations(true);
			logger.info("count of trial organizations = " + organizationsExpiryDtos.size());
			for (OrganizationsExpiryDto organizationsExpiryDto : organizationsExpiryDtos) {
				// emailServiceImpl.sendMail(mailDto);
				// mailDto.setSuccess("1");
				// emailServiceImpl.saveMail(mailDto);

				Date date1 = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss zzz yyyy");
				try {
					date1 = formatter.parse(organizationsExpiryDto.getEndDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Calendar now = Calendar.getInstance();

				int diffInDays = (int) ((date1.getTime() - now.getTimeInMillis()) / (1000 * 60 * 60 * 24)) + 1;

				if (diffInDays < 1) {
					ActDto actDto = new ActDto();
					actDto.setId(organizationsExpiryDto.getOrganizationId());

					activationServiceImpl.suspendNetlogicOrganization(actDto);
					// MailDto mailDto = new MailDto();
					// // mailDto.setUserId(userDto.getId());
					// // mailDto.setToken(userDto.getSecurityToken());
					// mailDto.setUserName(organizationsExpiryDto.getUsername());
					// mailDto.setMailSubject("Reminder S-Blox");
					// mailDto.setTemplateName("emailOwnerReminderTemplate.vm");
					// mailDto.setMailTo(organizationsExpiryDto.getEmail());
					// mailDto.setSuccess("-1");
					// // mailBusiness.saveMail(mailDto);
					//
					// logger.debug("TemplateName: emailOwnerServiceTemplate.vm");
					// logger.debug("Admin Email: " + mailDto.getMailTo());
					// logger.debug("Username: " + mailDto.getUserName());
					// logger.debug("MailSubject: Getting Started with S-Blox");
					//
					// emailServiceImpl.saveMail(mailDto);
				} else {
					continue;
				}

			}

		} catch (SchedulerException | BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// facade.notifyDriver(accountId);

	}

}
