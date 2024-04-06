package com.resdelivery.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.resdelivery.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order, String>{

	Optional<Order> findTopByOrderByIdDesc();
	Optional<Order> findByOrderId(String id);

	Optional<List<Order>> findByCustId(String uid);
	Optional<List<Order>> findAllByDeliveryStatus(String staffid);

}
