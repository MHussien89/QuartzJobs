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

import com.sblox.business.MailBusiness;
import com.sblox.common.dto.MailDto;
import com.sblox.common.exception.BaseException;
import com.sblox.service.MailService;
import com.sblox.common.util.Defines;

@Service
public class EmailServiceImpl implements MailService, Defines {

	@Autowired
	private JavaMailSender mailSender;
	private VelocityEngine velocityEngine;

	private static Logger logger = Logger.getLogger(EmailServiceImpl.class);

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

	@Autowired
	private MailBusiness mailBusiness;

	@Override
	public void sendMail(MailDto mailDto) {

		// UserDto userDto;
		// MailDto mail = new MailDto();
		// try {
		// String mode =
		// configurationService.getConfigurationValue(MOXTRA_MODE);
		// userDto = userBusiness.getUserById(userId);
		// mail.setUserId(mailDto.getUserId());
		// mail.setToken(mailDto.getToken());
		// mail.setUserName(mailDto.getUserName());
		// mail.setMailSubject("Getting Started with S-Blox");
		// mail.setTemplateName(mailDto.getTemplateName());
		// if(mode.equalsIgnoreCase(MOXTRA_MODE_PROD) && !adminMail){
		// mail.setMailTo(mailDto.getMailTo());
		// }else if(mode.equalsIgnoreCase(MOXTRA_MODE_PROD) && adminMail){
		// String superAdminMail =
		// configurationService.getConfigurationValue(ADMIN_MAIL);
		// mail.setMailTo(superAdminMail);
		// }else{
		// mail.setMailTo("mostafa.hussien.moawad@gmail.com");
		//// mail.setMailTo("khairallah@s-blox.com");
		// }

		// } catch (BaseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(mailDto.getMailTo());
				message.setSubject(mailDto.getMailSubject());
				message.setFrom("info@s-blox.com"); // could be parameterized...

				Map model = new HashMap();
				model.put("user", mailDto);
				model.put("name", mailDto.getUserName());
				if (mailDto.getEndDate() != null && !mailDto.getEndDate().equalsIgnoreCase("")) {
					model.put("endDate", mailDto.getEndDate());
				}
				String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
						"/templates/" + mailDto.getTemplateName(), model);
				message.setText(text, true);
				// mailSender.send(message);
			}
		};
		this.mailSender.send(preparator);

	}

	@Override
	public List<MailDto> getMailBySuccess(String success) throws BaseException {
		// TODO Auto-generated method stub
		return mailBusiness.getMailBySuccess(success);
	}

	@Override
	public void saveMail(MailDto mailDto) throws BaseException {
		mailBusiness.saveMail(mailDto);
	}

}
