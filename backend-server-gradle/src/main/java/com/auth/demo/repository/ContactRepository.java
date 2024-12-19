package com.auth.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.auth.demo.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {

}
