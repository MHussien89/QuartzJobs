package com.netlogic.dataaccess.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.netlogic.dataaccess.model.Act;

@Repository
public interface ActRepository extends CrudRepository<Act, Long> {

	public Act save(Act act) throws DataAccessException;

	public Act findById(Long id);

}