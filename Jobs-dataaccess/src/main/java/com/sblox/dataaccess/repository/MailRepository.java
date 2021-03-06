package com.sblox.dataaccess.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.sblox.dataaccess.model.Mail;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {

	public long count();

	public Mail save(Mail mail) throws DataAccessException;;

	public List<Mail> findBySuccess(String success);
	

}
