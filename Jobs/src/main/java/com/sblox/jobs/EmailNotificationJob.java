package com.sblox.jobs;

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

import com.sblox.common.dto.MailDto;
import com.sblox.common.exception.BaseException;
import com.sblox.service.MailService;

public class EmailNotificationJob implements Job {

	private Logger logger = Logger.getLogger(EmailNotificationJob.class);

	private MailService emailServiceImpl;

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		logger.debug("DriverNotificationJob.execute");
		SchedulerContext context;
		try {
			context = jec.getScheduler().getContext();

			// mailBusiness = (MailBusiness) context.get("mailBusiness");
			emailServiceImpl = (MailService) context.get("emailServiceImpl");

			List<MailDto> mailsDTOs = emailServiceImpl.getMailBySuccess("-1");
			logger.info("count of mails = " + mailsDTOs.size());
			for (MailDto mailDto : mailsDTOs) {
				if (mailDto.getMailTo() == null || mailDto.getMailSubject() == null || mailDto.getTemplateName() == null
						|| mailDto.getContentType() == null) {
					continue;
				}
				emailServiceImpl.sendMail(mailDto);
				mailDto.setSuccess("1");
				emailServiceImpl.saveMail(mailDto);
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
