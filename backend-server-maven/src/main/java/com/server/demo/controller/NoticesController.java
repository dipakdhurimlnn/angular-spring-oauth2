package com.server.demo.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.demo.entity.Notice;
import com.server.demo.repository.NoticeRepository;

@RestController
public class NoticesController {

	@Autowired
	private NoticeRepository noticeRepository;

	@GetMapping("/api/notices")
	public List<Notice> getNotices() {
		List<Notice> notices = noticeRepository.findAllActiveNotices();
		if (notices != null) {
			return notices;
		} else {
			return null;
		}
	}

}
