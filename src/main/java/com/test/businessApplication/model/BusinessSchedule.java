package com.test.businessApplication.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name="business_schedules")
public class BusinessSchedule implements Serializable  {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private BusinessSchedulePK scheduleId;
	
	@ManyToOne
	@JoinColumn(name="business_id", insertable = false, updatable = false)
	@JsonBackReference
	private BusinessClient business;
	
	@ManyToOne
	@JoinColumn(name="week_day_id", insertable = false, updatable = false)
	private WeekDay dayOfTheWeek;
	
	@Column(name="open_time", nullable = false)
	private Long openTime;
	
	@Column(name="close_time", nullable = false)
	private Long closeTime;
	
	public BusinessSchedulePK getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(BusinessSchedulePK scheduleId) {
		this.scheduleId = scheduleId;
	}

	public BusinessClient getBusiness() {
		return business;
	}

	public void setBusiness(BusinessClient business) {
		this.business = business;
	}

	public WeekDay getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(WeekDay dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}
	
	public Long getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Long openTime) {
		this.openTime = openTime;
	}

	public Long getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(Long closeTime) {
		this.closeTime = closeTime;
	}

	@Override
	public String toString() {
		return "BusinessSchedule [scheduleId=" + scheduleId + ", business=" + business + ", dayOfTheWeek="
				+ dayOfTheWeek + ", openTime=" + openTime + ", closeTime=" + closeTime + "]";
	}
	
}