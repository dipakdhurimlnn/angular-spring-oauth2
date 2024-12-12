package com.auth.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.auth.demo.entity.RegisteredClientEntity;
@Repository
public interface RegisteredClientJpaRepository extends JpaRepository<RegisteredClientEntity, String> {
	Optional<RegisteredClientEntity> findByClientId(String clientId);
}
