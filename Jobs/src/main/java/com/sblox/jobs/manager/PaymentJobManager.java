package com.sblox.jobs.manager;

import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.simpl.SimpleJobFactory;

//import static org.quartz.TriggerBuilder.*;
import static org.quartz.CronScheduleBuilder.*;
//import static org.quartz.DateBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sblox.common.exception.BaseException;
import com.sblox.jobs.EmailNotificationJob;
import com.sblox.jobs.OrganizationExpiryJob;

import org.apache.log4j.Logger;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@Component
public class PaymentJobManager {

	@Autowired
	private Scheduler scheduler;

	private Logger logger = Logger.getLogger(PaymentJobManager.class);

	@EventListener({ ContextRefreshedEvent.class })
	public void contextRefreshedEvent(ContextRefreshedEvent event) {
		logger.info("Starting Job Manager");
		try {
			Trigger emailTrigger = getMinutelyTrigger("EMAIL_TRIGGER");
			Trigger organizationExpiryTrigger = getDailyTrigger("Organization_Expiry_TRIGGER");
//			Trigger organizationExpiryTrigger = getMinutelyTrigger("Organization_Expiry_TRIGGER");
			scheduleNewJob("EMAIL_TRIGGER", "Email", EmailNotificationJob.class, emailTrigger);
			scheduleNewJob("ORGANIZATION_EXPIRY_TRIGGER", "OrgExpiry", OrganizationExpiryJob.class, organizationExpiryTrigger);
		} catch (BaseException e) {

		}
	}

	public void scheduleNewJob(String key, String jobGroup, Class klass, Trigger trigger, Object... params)
			throws BaseException {
		try {
			JobDataMap dataMap = new JobDataMap();
			for (int i = 0; i < params.length; i++) {
				dataMap.put("param" + i, params[i]);
			}
			JobKey jobKey = new JobKey(key, jobGroup);

			if (scheduler.checkExists(jobKey)) {
				scheduler.deleteJob(jobKey);
			}

			JobDetail job = JobBuilder.newJob(klass).setJobData(dataMap).withIdentity(jobKey).build();
			scheduler.setJobFactory(new SimpleJobFactory());
			scheduler.scheduleJob(job, trigger);
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new BaseException("Cannot create new quartz job");
		}
	}

	public Trigger getMinutelyTrigger(String key) throws BaseException {
		try {
			unscheduleJob(key);
		} catch (Exception ex) {
			throw new BaseException("Can't unschedule trigger");
		}

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey(key))
				.withSchedule(SimpleScheduleBuilder.repeatMinutelyForever()).build();

		return trigger;
	}

	public Trigger getDailyTrigger(String key) throws BaseException {
		try {
			unscheduleJob(key);
		} catch (Exception ex) {
			throw new BaseException("Can't unschedule trigger");
		}

		Trigger trigger = TriggerBuilder.newTrigger().withIdentity(new TriggerKey(key))
				.withSchedule(cronSchedule("0 20 14 1/1 * ? *")).build();

		return trigger;
	}

	private void unscheduleJob(String triggerKey) throws BaseException {
		try {
			TriggerKey key = new TriggerKey(triggerKey);
			Trigger trigger = scheduler.getTrigger(key);
			if (trigger != null) {
				scheduler.unscheduleJob(key);
			}
		} catch (Exception ex) {
			throw new BaseException("Cannot find trigger");
		}
	}
}
