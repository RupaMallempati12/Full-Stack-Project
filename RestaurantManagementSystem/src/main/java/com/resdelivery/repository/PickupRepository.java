package com.resdelivery.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.resdelivery.model.Pickup;

@Repository
public interface PickupRepository extends CrudRepository<Pickup, String>{

}
