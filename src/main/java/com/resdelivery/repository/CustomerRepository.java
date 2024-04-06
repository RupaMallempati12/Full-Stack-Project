package com.resdelivery.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.resdelivery.model.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, String>{

	Optional<Customer> findTopByOrderByIdDesc();

	Optional<Customer> findByEmailAndPassword(String email, String password);

	Optional<Customer> findByCustId(String string);


}
