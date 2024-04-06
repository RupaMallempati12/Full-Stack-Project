package com.resdelivery.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.resdelivery.model.Delivery;

@Repository
public interface DeliveryRepository extends CrudRepository<Delivery, String>{

	Optional<Delivery> findTopByOrderByIdDesc();

	Delivery findByOrderId(String orderId);

}
