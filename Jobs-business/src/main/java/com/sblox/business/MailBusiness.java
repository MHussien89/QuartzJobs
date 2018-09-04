package com.sblox.business;

import java.util.List;

import com.sblox.common.dto.MailDto;
import com.sblox.common.exception.BaseException;


public interface MailBusiness {


    public MailDto saveMail(MailDto mailDto) throws BaseException;

    public List<MailDto> getMailBySuccess(String success) throws BaseException;

   

}
