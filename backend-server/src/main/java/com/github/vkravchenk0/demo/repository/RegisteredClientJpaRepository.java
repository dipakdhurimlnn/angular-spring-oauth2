package com.github.vkravchenk0.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.github.vkravchenk0.demo.entity.RegisteredClientEntity;

public interface RegisteredClientJpaRepository extends JpaRepository<RegisteredClientEntity, String> {
	Optional<RegisteredClientEntity> findByClientId(String clientId);
}
