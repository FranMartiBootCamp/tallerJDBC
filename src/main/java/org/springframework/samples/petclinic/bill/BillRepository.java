package org.springframework.samples.petclinic.bill;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer>{
	
	@Transactional(readOnly = true)
    List<Bill> findAll() throws DataAccessException;
	
	Bill save(Bill bill);
	
}

