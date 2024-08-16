package com.github.vkravchenk0.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.github.vkravchenk0.demo.entity.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Integer> {

	@Query(value = "from Notice n where CURDATE() BETWEEN noticBegDt AND noticEndDt")
	List<Notice> findAllActiveNotices();

}
