package com.jerry.sample.widget.calendar;

import com.google.gson.annotations.Expose;

public class CalendarInfoBean {

	public static final String SCHEDULING_PROGRAM_EXPIRED = "0";  // 过期
	public static final String SCHEDULING_PROGRAM_IDLE = "1";  // 空闲
	public static final String SCHEDULING_PROGRAM_BUSY = "2";  // 忙
	public static final String SCHEDULING_PROGRAM_ORDER = "3";  // 预定
	
	@Expose
	private int id;
	@Expose
	private String date;
	@Expose
	private String status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

}