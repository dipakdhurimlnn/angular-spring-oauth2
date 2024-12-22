package com.auth.demo.rest.model;

import java.util.Date;

import com.auth.demo.entity.Notice;

public class RestNotice {
	private int noticeId;

	private String noticeSummary;

	private String noticeDetails;

	private Date noticBegDt;

	private Date noticEndDt;

	private Date createDt;

	private Date updateDt;

	public RestNotice(Notice notice) {
		this.noticeId = notice.getNoticeId();
		this.noticeSummary = notice.getNoticeSummary();
		this.noticeDetails = notice.getNoticeDetails();
		this.noticBegDt = notice.getNoticBegDt();
		this.noticEndDt = notice.getNoticEndDt();
		this.createDt = notice.getCreateDt();
		this.updateDt = notice.getUpdateDt();
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getNoticeSummary() {
		return noticeSummary;
	}

	public void setNoticeSummary(String noticeSummary) {
		this.noticeSummary = noticeSummary;
	}

	public String getNoticeDetails() {
		return noticeDetails;
	}

	public void setNoticeDetails(String noticeDetails) {
		this.noticeDetails = noticeDetails;
	}

	public Date getNoticBegDt() {
		return noticBegDt;
	}

	public void setNoticBegDt(Date noticBegDt) {
		this.noticBegDt = noticBegDt;
	}

	public Date getNoticEndDt() {
		return noticEndDt;
	}

	public void setNoticEndDt(Date noticEndDt) {
		this.noticEndDt = noticEndDt;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

}
