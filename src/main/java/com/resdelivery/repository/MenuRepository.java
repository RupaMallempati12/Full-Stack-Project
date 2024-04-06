package com.resdelivery.repository;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.resdelivery.model.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, String>{

	Optional<Menu> findTopByOrderByIdDesc();


}
