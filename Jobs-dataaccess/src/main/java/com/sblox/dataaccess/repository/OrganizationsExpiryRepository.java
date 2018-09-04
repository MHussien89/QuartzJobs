package com.sblox.dataaccess.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sblox.common.exception.BaseException;
import com.sblox.dataaccess.model.OrganizationsExpiry;

@Repository
public interface OrganizationsExpiryRepository extends CrudRepository<OrganizationsExpiry, Long> {

	public long count();

	// public OrganizationsExpiry save(OrganizationsExpiry mail) throws
	// DataAccessException;;

	public List<OrganizationsExpiry> findByActive(boolean active);

	public List<OrganizationsExpiry> findByTrial(boolean trial);

}
