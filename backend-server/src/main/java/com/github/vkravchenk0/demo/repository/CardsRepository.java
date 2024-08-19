package com.github.vkravchenk0.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.vkravchenk0.demo.entity.Cards;

@Repository
public interface CardsRepository extends CrudRepository<Cards, Integer> {

	List<Cards> findByCustomerId(int customerId);

}
