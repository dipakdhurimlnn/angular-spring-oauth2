package com.spring.auth.server.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.auth.server.demo.entity.Notice;

@Repository
public interface NoticeRepository extends CrudRepository<Notice, Integer> {

	@Query(nativeQuery = true, value = "SELECT n FROM Notice n WHERE CURRENT_DATE BETWEEN n.notic_beg_dt AND n.notic_end_dt")
	List<Notice> findAllActiveNotices();

}