package com.auth.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth.demo.entity.Notice;
import com.auth.demo.repository.NoticeRepository;
import com.auth.demo.rest.model.RestNotice;

@RestController
public class NoticesController {

	@Autowired
	private NoticeRepository noticeRepository;

	@GetMapping("/api/notices")
	public List<RestNotice> getNotices() {
		System.err.println("/notices");
		List<Notice> notices = noticeRepository.findAllActiveNotices();
		System.err.println(notices.size());
		if (notices != null) {
			return notices.stream().map(at -> new RestNotice(at)).collect(Collectors.toList());
		} else {
			return null;
		}
	}

}
