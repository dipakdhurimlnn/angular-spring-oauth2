package com.server.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.server.demo.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {

}
