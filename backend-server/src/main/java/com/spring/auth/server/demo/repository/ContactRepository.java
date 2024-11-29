package com.spring.auth.server.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.auth.server.demo.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {

}
