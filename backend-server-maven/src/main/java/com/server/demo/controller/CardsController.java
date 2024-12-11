package com.server.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.demo.entity.Cards;
import com.server.demo.repository.CardsRepository;

@RestController
public class CardsController {

	@Autowired
	private CardsRepository cardsRepository;

	@GetMapping("/api/myCards")
	public List<Cards> getCardDetails(@RequestParam int id) {
		List<Cards> cards = cardsRepository.findByCustomerId(id);
		if (cards != null) {
			return cards;
		} else {
			return null;
		}
	}

}
