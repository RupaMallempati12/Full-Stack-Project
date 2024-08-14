package com.resdelivery.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.resdelivery.model.Delivery;
import com.resdelivery.model.Staff;

@Repository
public interface StaffRepository extends CrudRepository<Staff, String>{

	Optional<Staff> findTopByOrderByIdDesc();

	Optional<Staff> findByEmailAndPassword(String email, String password);
	List<Staff> findByStafftype(String stafftype);

	
}
