package com.github.vkravchenk0.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.vkravchenk0.demo.entity.Contact;

@Repository
public interface ContactRepository extends CrudRepository<Contact, String> {

}
