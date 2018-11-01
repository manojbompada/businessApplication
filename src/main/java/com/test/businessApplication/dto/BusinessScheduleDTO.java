package com.test.businessApplication.dto;


public class BusinessScheduleDTO {
	private Integer dayId;
	private String openTime;
	private String closeTime;
	public Integer getDayId() {
		return dayId;
	}
	public void setDayId(Integer dayId) {
		this.dayId = dayId;
	}
	public String getOpenTime() {
		return openTime;
	}
	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}
	public String getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}
	

}
